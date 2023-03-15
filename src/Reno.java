public class Reno implements Runnable{

    private Buffer sharedResource;
    private boolean isArrive;
    private int index;

    public Reno(Buffer sharedResource,int index){
        this.sharedResource = sharedResource;
        this.index = index;
    }

    @Override
    public void run() {
        while (true){
            try {
                //ver si esta el grupo de 3 duendes
                Thread.sleep(1000);
                if (!sharedResource.isReindeerArrive(index)){
                    sharedResource.llegoUnReno(index);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
