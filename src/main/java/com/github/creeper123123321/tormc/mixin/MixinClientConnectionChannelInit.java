package com.github.creeper123123321.tormc.mixin;

import io.netty.channel.Channel;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.proxy.Socks5ProxyHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.net.InetSocketAddress;

@Mixin(targets = "net.minecraft.network.ClientConnection$1")
public class MixinClientConnectionChannelInit {
	@Inject(method = "initChannel", at = @At(value = "TAIL"), remap = false)
	private void onInitChannel(Channel channel, CallbackInfo ci) {
		if (channel instanceof SocketChannel) {
			channel.pipeline()
					.addFirst(new Socks5ProxyHandler(new InetSocketAddress("localhost", 9050)));
		}
	}
}
