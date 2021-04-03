package com.github.creeper123123321.tormc.mixin;

import net.minecraft.client.network.MultiplayerServerListPinger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Mixin(MultiplayerServerListPinger.class)
public class MixinMultiplayerServerListPinger {
	@Redirect(method = "add", at = @At(value = "INVOKE", target = "Ljava/net/InetAddress;getByName(Ljava/lang/String;)Ljava/net/InetAddress;"))
	private InetAddress unresolveAddress(String host) throws UnknownHostException {
		return InetAddress.getByAddress(host, new byte[] {-1, -1, -1, -1});
	}
}
