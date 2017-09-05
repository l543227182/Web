package cn.lcstudio.utils;

/**
 * Created by luochao.byron on 2017/9/4.
 */
public class computeSemblance {
    public static void main(String args[]){
        System.out.println(compute("123321","123321"));
    }
    public static float compute(String str1,String str2) {
        //璁＄畻涓や釜瀛楃涓茬殑闀垮害銆�
        int len1 = str1.length();
        int len2 = str2.length();
        //寤虹珛涓婇潰璇寸殑鏁扮粍锛屾瘮瀛楃闀垮害澶т竴涓┖闂�
        int[][] dif = new int[len1 + 1][len2 + 1];
        //璧嬪垵鍊硷紝姝ラB銆�
        for (int a = 0; a <= len1; a++) {
            dif[a][0] = a;
        }
        for (int a = 0; a <= len2; a++) {
            dif[0][a] = a;
        }
        //璁＄畻涓や釜瀛楃鏄惁涓�牱锛岃绠楀乏涓婄殑鍊�
        int temp;
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    temp = 0;
                } else {
                    temp = 1;
                }
                //鍙栦笁涓�涓渶灏忕殑
                dif[i][j] = min(dif[i - 1][j - 1] + temp, dif[i][j - 1] + 1,
                        dif[i - 1][j] + 1);
            }
        }
        //璁＄畻鐩镐技搴�
        float similarity =1 - (float) dif[len1][len2] / Math.max(str1.length(), str2.length());
        return similarity;
    }

    //寰楀埌鏈�皬鍊�
    private static int min(int... is) {
        int min = Integer.MAX_VALUE;
        for (int i : is) {
            if (min > i) {
                min = i;
            }
        }
        return min;
    }

}
