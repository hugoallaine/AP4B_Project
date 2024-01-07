public class Passif{
    public static  final int weak_Japanese = 1;
    public static  final int strong_Japanese = 2;
    public static  final int weak_English = 3;
    public static  final int strong_English = 4;
    public static  final int weak_Spanish = 5;
    public static  final int strong_Spanish = 6;
    public static  final int weak_Chinese = 7;
    public static  final int strong_Chinese = 8;
    public static  final int weak_German = 9;
    public static  final int strong_German = 10;
    public static  final int weak_Italian = 11;
    public static  final int strong_Italian = 12;

    public static void weak_Japanese(Monster monstre ,Player target){
        if (target.getLanguage() == Language.JAPANESE){
            monstre.setPower(monstre.getPower()-5);
        }
    }
    public static void strong_Japanese(Monster monstre ,Player target){
        if (target.getLanguage() == Language.ENGLISH){
            monstre.setPower(monstre.getPower()+5);
        }
    }
    public static void weak_English(Monster monstre ,Player target){
        if (target.getLanguage() == Language.ENGLISH){
            monstre.setPower(monstre.getPower()-5);
        }
    }
    public static void strong_English(Monster monstre ,Player target){
        if (target.getLanguage() == Language.ENGLISH){
            monstre.setPower(monstre.getPower()+5);
        }
    }
    public static void weak_Spanish(Monster monstre ,Player target){
        if (target.getLanguage() == Language.SPANISH){
            monstre.setPower(monstre.getPower()-5);
        }
    }
    public static void strong_Spanish(Monster monstre ,Player target){
        if (target.getLanguage() == Language.SPANISH){
            monstre.setPower(monstre.getPower()+5);
        }
    }
    public static void weak_Chinese(Monster monstre ,Player target){
        if (target.getLanguage() == Language.CHINESE){
            monstre.setPower(monstre.getPower()-5);
        }
    }
    public static void strong_Chinese(Monster monstre ,Player target){
        if (target.getLanguage() == Language.CHINESE){
            monstre.setPower(monstre.getPower()+5);
        }
    }
    public static void weak_German(Monster monstre ,Player target){
        if (target.getLanguage() == Language.GERMAN){
            monstre.setPower(monstre.getPower()-5);
        }
    }
    public static void strong_German(Monster monstre ,Player target){
        if (target.getLanguage() == Language.GERMAN){
            monstre.setPower(monstre.getPower()+5);
        }
    }
    public static void weak_Italian(Monster monstre ,Player target){
        if (target.getLanguage() == Language.ITALIAN){
            monstre.setPower(monstre.getPower()-5);
        }
    }
    public static void strong_Italian(Monster monstre ,Player target){
        if (target.getLanguage() == Language.ITALIAN){
            monstre.setPower(monstre.getPower()+5);
        }
    }

public static EffectFunction getEffectFunctionFromID(int ID) {
        switch(ID) {
            case weak_Japanese:: 
                return ((Monster target) -> weak_Japanese(target));
            case strong_Japanese:
                return ((Monster target) -> strong_Japanese(target));
            case weak_English:
                return ((Monster target) -> weak_English(target));
            case strong_English:
                return ((Monster target) -> strong_English(target));
            case weak_Spanish:
                return ((Monster target) -> weak_Spanish(target));
            case strong_Spanish:    
                return ((Monster target) -> strong_Spanish(target));
            case weak_Chinese:
                return ((Monster target) -> weak_Chinese(target));
            case strong_Chinese:
                return ((Monster target) -> strong_Chinese(target));
            case weak_German:
                return ((Monster target) -> weak_German(target));
            case strong_German:
                return ((Monster target) -> strong_German(target));
            case weak_Italian:
                return ((Monster target) -> weak_Italian(target));
            case strong_Italian:
                return ((Monster target) -> strong_Italian(target));
            default:
                return null;
        }
    }
            
}