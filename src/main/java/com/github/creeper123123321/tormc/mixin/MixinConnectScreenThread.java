package com.github.creeper123123321.tormc.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Mixin(targets = "net/minecraft/client/gui/screen/ConnectScreen$1")
public class MixinConnectScreenThread {
	@Redirect(method = "run()V", at = @At(value = "INVOKE",
			target = "Ljava/net/InetAddress;getByName(Ljava/lang/String;)Ljava/net/InetAddress;"))
	private InetAddress unresolveAddress(String address) throws UnknownHostException {
		return InetAddress.getByAddress(address, new byte[]{-1, -1, -1, -1});
	}
}
