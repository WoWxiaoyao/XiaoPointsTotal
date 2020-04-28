package zbv5.cn.XiaoPointsTotal.util.PlaceholderAPIHook;

import zbv5.cn.XiaoPointsTotal.Main;

public class HookMain
{

    public static void hook(String version)
    {
        if(isNew(version))
        {
            new NewHook(Main.getInstance()).register();
        } else {
            new OldHook(Main.getInstance()).hook();
        }
    }

    public static boolean isNew(String version)
    {
        return compareVersion(version,"2.10.4") >= 0;
    }

    public static int compareVersion(String v1, String v2)
    {
        if (v1.equals(v2))
        {
            return 0;
        }
        String[] version1Array = v1.split("[._]");
        String[] version2Array = v2.split("[._]");
        int index = 0;
        int minLen = Math.min(version1Array.length, version2Array.length);
        long diff = 0;

        while (index < minLen
                && (diff = Long.parseLong(version1Array[index])
                - Long.parseLong(version2Array[index])) == 0) {
            index++;
        }
        if (diff == 0)
        {
            for (int i = index; i < version1Array.length; i++)
            {
                if (Long.parseLong(version1Array[i]) > 0) {
                    return 1;
                }
            }

            for (int i = index; i < version2Array.length; i++)
            {
                if (Long.parseLong(version2Array[i]) > 0) {
                    return -1;
                }
            }
            return 0;
        } else {
            return diff > 0 ? 1 : -1;
        }
    }
}
