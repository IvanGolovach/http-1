package net.golovach.junior.http.EXAMPLE;

import java.net.InetAddress;
import java.util.Arrays;

public class InetAddressTest {
    public static void main(String[] args) throws Exception {
        describe("local", InetAddress.getLocalHost());
        describe("loopBack", InetAddress.getLoopbackAddress());
        describe("any", InetAddress.getByAddress(new byte[]{0, 0, 0, 0}));
    }

    private static void describe(String name, InetAddress address) {
        System.out.println(name + ": " + address);
        System.out.println("    address.getHostAddress() = " + address.getHostAddress());
        System.out.println("    address.getHostName() = " + address.getHostName());
        System.out.println("    address.getCanonicalHostName() = " + address.getCanonicalHostName());
        System.out.println("    address.getAddress() = " + Arrays.toString(address.getAddress()));

        System.out.println("    address.isAnyLocalAddress() = " + address.isAnyLocalAddress());
        System.out.println("    address.isLinkLocalAddress() = " + address.isLinkLocalAddress());
        System.out.println("    address.isLoopbackAddress() = " + address.isLoopbackAddress());
        System.out.println("    address.isMulticastAddress() = " + address.isMulticastAddress());
        System.out.println("    address.isSiteLocalAddress() = " + address.isSiteLocalAddress());

        System.out.println("    address.isMCGlobal() = " + address.isMCGlobal());
        System.out.println("    address.isMCLinkLocal() = " + address.isMCLinkLocal());
        System.out.println("    address.isMCOrgLocal() = " + address.isMCOrgLocal());
        System.out.println("    address.isMCNodeLocal() = " + address.isMCNodeLocal());
        System.out.println("    address.isMCSiteLocal() = " + address.isMCSiteLocal());
    }
}
