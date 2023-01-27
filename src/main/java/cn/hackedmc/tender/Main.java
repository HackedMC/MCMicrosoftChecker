package cn.hackedmc.tender;

import cn.hackedmc.tender.utils.accound.AccountChecker;
import cn.hackedmc.tender.utils.accound.MCAccount;
import cn.hackedmc.tender.utils.config.Config;
import cn.hackedmc.tender.utils.porxy.ProxyHelper;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static final String colorReset = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String colorCyan = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static ArrayList<MCAccount> checkedAlts = new ArrayList<>();
    public static ArrayList<ProxyHelper> badProxys = new ArrayList<>();
    public static ArrayList<ProxyHelper> proxies = new ArrayList<>();
    public static ArrayList<MCAccount> alts = new ArrayList<>();
    private static boolean checking = true;
    private static Thread[] threads;
    private static final Config config = new Config();

    public static void main(String[] args) {
        checking = true;
        config.read();
        alts = config.getAC();
        if (alts.isEmpty()) {
            print("没有找到你的Combo文件或你的Combo文件夹为空！", ANSI_YELLOW);
            return;
        }
        proxies = config.getProxy();
        if (proxies.isEmpty()) {
            print("没有找到你的Proxy文件或你的Proxy文件为空!", ANSI_YELLOW);
            return;
        }

        config.createHits();

        print("OK啊，输出文件夹创造完成，现在读取" + alts.size() + "张Combo以及" + proxies.size() + "张代理，希望你这次测卡是愉快的！", ANSI_GREEN);

        threads = new Thread[config.getThreadCount()];
        AccountChecker.setOrigSize(alts.size());

        for (int i = 0; i < config.getThreadCount(); i++) {
            (threads[i] = new AccountChecker(config)).start();
        }

        print("本次美丽的测卡已经启航！", ANSI_GREEN);

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            print("我操崩端了，快点去问美丽的Dev", ANSI_RED);
            throw new RuntimeException(e);
        }

        while (checking) {
            try {
                AccountChecker.status();
                Thread.sleep(150L);
            } catch (InterruptedException e) {
                print("神必原因崩端了，快点去问Dev，别自己在这瞎几把捣鼓然后给捣鼓炸了！！！！！！", ANSI_RED);
                throw new RuntimeException(e);
            }
        }

        AccountChecker.done();
        print("OK啊！本次测卡结束，用户再见辣~~~", ANSI_GREEN);
        System.exit(0);
    }

    public static void print(String s, String color) {
        System.out.println(color + s + colorReset);
    }

    public static MCAccount getAccount() {
        int size = alts.size();
        if (size == 0)
            return null;
        Random random = new Random();
        int select = random.nextInt(size);
        return alts.get(select);
    }

    public static void stop() {
        checking = false;
        for (Thread thread : threads) {
            thread.stop();
        }
    }

    public static ProxyHelper getProxy() {
        int size = proxies.size();
        if (size == 0)
            return null;
        Random random = new Random();
        int select = random.nextInt(size);
        if (proxies.get(select) == null) {
            stop();
            return null;
        }
        return proxies.get(select);
    }
}