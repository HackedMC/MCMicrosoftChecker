package cn.hackedmc.tender.utils.config;

import cn.hackedmc.tender.Main;
import cn.hackedmc.tender.utils.accound.MCAccount;
import cn.hackedmc.tender.utils.porxy.ProxyHelper;

import java.io.File;
import java.io.IOException;
import java.net.Proxy;
import java.util.ArrayList;

public class Config {
    private final String configPath = new File("").getAbsolutePath();
    private int threadCount;
    private boolean printFails;
    private Proxy.Type proxyMode;

    private File outputFile;
    private File comboFile;
    private File proxyFile;

    public void read() {
        final File config = new File(configPath, "Setting.ini");
        ConfigHelper ch;
        if (config.canRead()) {
            ch = new ConfigHelper(FileUtils.readFile(config));
        } else {
            try {
                config.createNewFile();
                ch = new ConfigHelper(FileUtils.readFile(config));
            } catch (IOException e) {
                Main.print("未知错误！", Main.ANSI_RED);
                throw new RuntimeException(e);
            }
        }
        this.printFails = ch.getBool("输出Bad卡", true);
        this.threadCount = ch.getInt("线程数", 50);
        switch (ch.getString("代理模式", "HTTPS").toLowerCase()) {
            case "http":
            case "https":
                this.proxyMode = Proxy.Type.HTTP;
                break;
            case "sock4":
            case "sock5":
                this.proxyMode = Proxy.Type.SOCKS;
                break;
            default:
                this.proxyMode = Proxy.Type.DIRECT;
                break;
        }

        outputFile = new File(configPath, "Hits.txt");
        comboFile = new File(configPath, "Combo.txt");
        proxyFile = new File(configPath, "Proxy.txt");
    }

    public File getOutputFile() {
        return outputFile;
    }

    public boolean isPrintFails() {
        return printFails;
    }

    public int getThreadCount() {
        return threadCount;
    }

    public ArrayList<MCAccount> getAC() {
        final String text = FileUtils.readFile(comboFile);
        final ArrayList<MCAccount> alts = new ArrayList<>();

        for (String alt : text.split("\n")) {
            alts.add(new MCAccount(alt.split(":")[0], alt.split(":")[1]));
        }

        return alts;
    }

    public ArrayList<ProxyHelper> getProxy() {
        final String text = FileUtils.readFile(proxyFile);
        final ArrayList<ProxyHelper> proxys = new ArrayList<>();

        for (String proxy : text.split("\n")) {
            proxys.add(new ProxyHelper(proxy.split(":")[0], Integer.parseInt(proxy.split(":")[1]), proxyMode));
        }

        return proxys;
    }

    public void createHits() {
        if (!this.outputFile.exists()) {
            Main.print("正在几把尝试生成美丽的Hits文件夹", Main.ANSI_YELLOW);
            try {
                if (!this.outputFile.createNewFile()) {
                    Main.print("我操生成爆炸了，请手动删除一遍", Main.ANSI_RED);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
