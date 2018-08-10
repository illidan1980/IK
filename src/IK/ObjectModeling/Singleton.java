package IK.ObjectModeling;

/**
 * Created  on 6/5/2016.
 */
public class Singleton {
    private static final Singleton eagerSingleton = new Singleton();
    private Singleton(){

    }
    public static Singleton getInstance(){
        return eagerSingleton;
    }
}

class LazySingleton{
    private static volatile LazySingleton self;
    private LazySingleton(){}

    public static LazySingleton getInstance(){
        if(self != null){
            return self;
        }
        synchronized (LazySingleton.class){
            if(self == null){
                self = new LazySingleton();
            }
        }
        return self;
    }
}

class HolderSingleton{
    private HolderSingleton (){}
    public static HolderSingleton getInstance(){
        return MyHolder.instance;
    }
    private static class MyHolder{
        private static final HolderSingleton instance = new HolderSingleton();
    }
}