public class Santa implements Runnable{
    //Santa solo se despierta si los nueve renos llegan de vacaciones
    // O si 3 grupos de duendes necesitan ayuda.

    private Buffer buffer;

    public Santa(Buffer sharedBuffer){
        this.buffer = sharedBuffer;
    }

    @Override
    public void run() {
        while(true){
            try {
                //ver cuantos duendes necesitan mi ayuda

                buffer.awakeSanta();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
