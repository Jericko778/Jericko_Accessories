package net.jericko.accessories.client;

public class ClientBulletData {

    private static int bullets = 6;

    public static void reloadBullet(){
        if(bullets < 6){
            bullets++;
        }
    }

    public static void useBullet(){
        if(bullets > 0){
            bullets--;
        }
    }

    public static boolean hasBullet(){
        return (bullets != 0);
    }

    public static boolean fullBullets(){
        return (bullets == 6);
    }

    public static int getBullets(){
        return bullets;
    }

}
