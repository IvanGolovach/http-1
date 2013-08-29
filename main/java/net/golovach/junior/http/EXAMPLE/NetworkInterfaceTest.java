package net.golovach.junior.http.EXAMPLE;

import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * http://docs.oracle.com/javase/tutorial/networking/nifs/index.html
 */
public class NetworkInterfaceTest {
    public static void main(String[] args) throws SocketException {
        Enumeration<NetworkInterface> iter = NetworkInterface.getNetworkInterfaces();
        while (iter.hasMoreElements()) {
            describe(iter.nextElement());
        }
    }

    private static void describe(NetworkInterface net) throws SocketException {
        System.out.println("Name:" + net.getName());
        System.out.println("    DisplayName:" + net.getDisplayName());
        System.out.println("    isLoopback:" + net.isLoopback());
        System.out.println("    isPointToPoint:" + net.isPointToPoint());
        System.out.println("    supportsMulticast:" + net.supportsMulticast());
        System.out.println("    isUp:" + net.isUp());
        System.out.println("    isVirtual:" + net.isVirtual());
        System.out.println("    HardwareAddress:" + Arrays.toString(net.getHardwareAddress()));
        System.out.println("    MTU:" + net.getMTU());
        System.out.println("    Parent:" + net.getParent());
        printlnSubInterfaces(net);
        printlnInetAddresses(net);
        printlnInterfaceAddresses(net);
    }

    private static void printlnSubInterfaces(NetworkInterface net) {
        System.out.print("    SubInterfaces:");
        Enumeration<NetworkInterface> iter = net.getSubInterfaces();
        if (!iter.hasMoreElements()) {
            System.out.println("-");
        } else {
            System.out.println();
            while (iter.hasMoreElements()) {
                System.out.println("        " + iter.nextElement());
            }
        }
    }

    private static void printlnInetAddresses(NetworkInterface net) {
        System.out.print("    InetAddresses:");
        Enumeration<NetworkInterface> iter = net.getSubInterfaces();
        if (!iter.hasMoreElements()) {
            System.out.println("-");
        } else {
            System.out.println();
            while (iter.hasMoreElements()) {
                System.out.println("        " + iter.nextElement());
            }
        }
    }

    private static void printlnInterfaceAddresses(NetworkInterface net) {
        System.out.print("    InterfaceAddresses:");
        if (net.getInterfaceAddresses().isEmpty()) {
            System.out.println("-");
        } else {
            System.out.println();
            for (InterfaceAddress address : net.getInterfaceAddresses()) {
                System.out.println("        " + address.toString() + ":");
                System.out.println("            Address:" + address.getAddress());
                System.out.println("            Broadcast:" + address.getBroadcast());
                System.out.println("            NetworkPrefixLength:" + address.getNetworkPrefixLength());
            }
        }
    }
}
