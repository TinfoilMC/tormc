package com.github.creeper123123321.tormc.mixin;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.resolver.NoopAddressResolverGroup;
import net.minecraft.network.ClientConnection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.net.InetAddress;
import java.net.InetSocketAddress;

@Mixin(ClientConnection.class)
public class MixinClientConnection {
	@Redirect(method = "connect", at = @At(value = "INVOKE",
			target = "Lio/netty/bootstrap/Bootstrap;connect(Ljava/net/InetAddress;I)Lio/netty/channel/ChannelFuture;"))
	private static ChannelFuture unresolveAddress(Bootstrap bootstrap, InetAddress inetHost, int inetPort) {
		return bootstrap
				.resolver(NoopAddressResolverGroup.INSTANCE)
				.connect(InetSocketAddress.createUnresolved(inetHost.getHostName(), inetPort));
	}
}