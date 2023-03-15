public class Duende implements Runnable{

    private Buffer sharedBuffer;
    private String name;

    public Duende(Buffer sharedBuffer, String name) {
        this.sharedBuffer = sharedBuffer;
    }

    @Override
    public void run() {
        while (true){
            try {
                //Si el grupo de 3 duendes esta Notificar a santa
                Thread.sleep(1000);
                sharedBuffer.incrementNumberOfElfsThatNeedHelp();
            }catch(InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
