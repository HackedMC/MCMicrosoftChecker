package cn.hackedmc.tender.utils.porxy;

import cn.hackedmc.tender.Main;
import cn.hackedmc.tender.utils.accound.AltHelper;

import java.io.IOException;
import java.net.Proxy;
import java.util.ArrayList;

public class ProxyHelper {

    private String ip;
    private int port;
    private int loginFails;
    private Proxy.Type type;

    public ProxyHelper(String ip, int port, Proxy.Type type) {
        this.ip = ip;
        this.port = port;
        this.type = type;
    }

    public static ArrayList<ProxyHelper> downloadProxyList() throws IOException {
        ArrayList<ProxyHelper> proxies = new ArrayList<>();
        String[] list = AltHelper.readURL("https://raw.githubusercontent.com/TheSpeedX/PROXY-List/master/http.txt").split("\n");
        Main.print("读取 " + list.length + " 张HTTP代理来自GitHub的TheSpeedX/PROXY-List Github", Main.colorCyan);
        for (String s : list) {
            try {
                String ip = s.split(":")[0];
                int port = Integer.parseInt(s.split(":")[1]);
                ProxyHelper proxyHelper = new ProxyHelper(ip, port, Proxy.Type.HTTP);
                proxies.add(proxyHelper);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException exception) {
                exception.printStackTrace(System.out);
            }
        }
        list = AltHelper.readURL("https://raw.githubusercontent.com/TheSpeedX/PROXY-List/master/socks4.txt").split("\n");
        Main.print("读取 " + list.length + "张SOCKS4代理来自GitHub的TheSpeedX/PROXY-List Github", Main.colorCyan);
        for (String s : list) {
            try {
                String ip = s.split(":")[0];
                int port = Integer.parseInt(s.split(":")[1]);
                ProxyHelper proxyHelper = new ProxyHelper(ip, port, Proxy.Type.SOCKS);
                proxies.add(proxyHelper);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException exception) {
                exception.printStackTrace(System.out);
            }
        }
        list = AltHelper.readURL("https://raw.githubusercontent.com/TheSpeedX/PROXY-List/master/socks5.txt").split("\n");
        Main.print("读取 " + list.length + " 张SOCKS5代理来自GitHub的TheSpeedX/PROXY-List Github", Main.colorCyan);
        for (String s : list) {
            try {
                String ip = s.split(":")[0];
                int port = Integer.parseInt(s.split(":")[1]);
                ProxyHelper proxyHelper = new ProxyHelper(ip, port, Proxy.Type.SOCKS);
                proxies.add(proxyHelper);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException exception) {
                exception.printStackTrace(System.out);
            }
        }
        list = AltHelper.readURL("https://raw.githubusercontent.com/ShiftyTR/Proxy-List/master/https.txt").split("\n");
        Main.print("读取 " + list.length + "张HTTPS代理来自GitHub的ShiftyTR/Proxy-List Github", Main.colorCyan);
        for (String s : list) {
            try {
                String ip = s.split(":")[0];
                int port = Integer.parseInt(s.split(":")[1]);
                ProxyHelper proxyHelper = new ProxyHelper(ip, port, Proxy.Type.SOCKS);
                proxies.add(proxyHelper);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException exception) {
                exception.printStackTrace(System.out);
            }
        }
        list = AltHelper.readURL("https://raw.githubusercontent.com/ShiftyTR/Proxy-List/master/http.txt").split("\n");
        Main.print("读取 " + list.length + "张HTTP代理来自GitHub的ShiftyTR/Proxy-List Github", Main.colorCyan);
        for (String s : list) {
            try {
                String ip = s.split(":")[0];
                int port = Integer.parseInt(s.split(":")[1]);
                ProxyHelper proxyHelper = new ProxyHelper(ip, port, Proxy.Type.SOCKS);
                proxies.add(proxyHelper);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException exception) {
                exception.printStackTrace(System.out);
            }
        }
        list = AltHelper.readURL("https://raw.githubusercontent.com/ShiftyTR/Proxy-List/master/socks4.txt").split("\n");
        Main.print("读取 " + list.length + "张SOCKS4代理来自GitHub的ShiftyTR/Proxy-List Github", Main.colorCyan);
        for (String s : list) {
            try {
                String ip = s.split(":")[0];
                int port = Integer.parseInt(s.split(":")[1]);
                ProxyHelper proxyHelper = new ProxyHelper(ip, port, Proxy.Type.SOCKS);
                proxies.add(proxyHelper);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException exception) {
                exception.printStackTrace(System.out);
            }
        }
        list = AltHelper.readURL("https://raw.githubusercontent.com/ShiftyTR/Proxy-List/master/socks5.txt").split("\n");
        Main.print("读取 " + list.length + "张SOCKS5代理来自GitHub的ShiftyTR/Proxy-List Github", Main.colorCyan);
        for (String s : list) {
            try {
                String ip = s.split(":")[0];
                int port = Integer.parseInt(s.split(":")[1]);
                ProxyHelper proxyHelper = new ProxyHelper(ip, port, Proxy.Type.SOCKS);
                proxies.add(proxyHelper);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException exception) {
                exception.printStackTrace(System.out);
            }
        }
        list = AltHelper.readURL("https://raw.githubusercontent.com/jetkai/proxy-list/main/online-proxies/txt/proxies-http%2Bhttps.txt").split("\n");
        Main.print("读取 " + list.length + "张HTTP以及HTTPS代理来自GitHub的jetkai/proxy-list Github", Main.colorCyan);
        for (String s : list) {
            try {
                String ip = s.split(":")[0];
                int port = Integer.parseInt(s.split(":")[1]);
                ProxyHelper proxyHelper = new ProxyHelper(ip, port, Proxy.Type.SOCKS);
                proxies.add(proxyHelper);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException exception) {
                exception.printStackTrace(System.out);
            }
        }
        list = AltHelper.readURL("https://raw.githubusercontent.com/jetkai/proxy-list/main/online-proxies/txt/proxies-socks4%2B5.txt").split("\n");
        Main.print("读取 " + list.length + "张SOCKS4以及SOCKS5代理来自GitHub的jetkai/proxy-list Github", Main.colorCyan);
        for (String s : list) {
            try {
                String ip = s.split(":")[0];
                int port = Integer.parseInt(s.split(":")[1]);
                ProxyHelper proxyHelper = new ProxyHelper(ip, port, Proxy.Type.SOCKS);
                proxies.add(proxyHelper);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException exception) {
                exception.printStackTrace(System.out);
            }
        }
        return proxies;
    }

    //gotta be a much better way to do this
    public static ArrayList<ProxyHelper> removeDuplicates(ArrayList<ProxyHelper> loginProxies) {
        ArrayList<ProxyHelper> out = new ArrayList<>();
        for (ProxyHelper proxyHelper : loginProxies) {
            boolean contains = false;
            for (ProxyHelper proxy : out) {
                if (proxyHelper.ip.equalsIgnoreCase(proxy.ip) && proxyHelper.port == proxy.port) {
                    contains = true;
                    break;
                }
            }
            if (!contains) {
                out.add(proxyHelper);
            }
        }
        return out;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getLoginFails() {
        return loginFails;
    }

    public void incLoginFails() {
        this.loginFails++;
    }

    public Proxy.Type getType() {
        return type;
    }

    public void setType(Proxy.Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return getIp() + ":" + getPort();
    }
}