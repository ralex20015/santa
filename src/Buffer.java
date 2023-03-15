import java.security.SecureRandom;
import java.util.Arrays;

public class Buffer {

    private int numOfElfThatNeedHelp;
    //Si santa ayuda a los renos entonces reseteamos;
    private int numOfReindeerThatArrive;
    private boolean isReindeerNeedingHelp;
    private boolean isElfNeedingHelp;
    //Controla acceso de duendes y renos;
    private boolean mutex;
    private boolean santaNeedsTOAwake;
    private SecureRandom generator = new SecureRandom();
    private int timesToHelpTheElf;
    private int randomTimeInSeconds;
    private boolean isSantaAwake;
    private boolean reinderThatArrive [] = new boolean[9];
   // private final Santa santa;
    //Hacer un metodo para generar

    public Buffer(){
        randomTimeInSeconds = generator.nextInt(2);
        System.out.println("Santa duerme");
        System.out.println();
    }
    public synchronized void incrementNumberOfElfsThatNeedHelp() throws InterruptedException {
        while (numOfReindeerThatArrive == 9){
            wait();
        }
        while(timesToHelpTheElf > 0){
           wait();
           notifyAll();
        }
        if (randomTimeInSeconds == 0){
            numOfElfThatNeedHelp++;

            if (numOfElfThatNeedHelp % 3 == 0){
                timesToHelpTheElf++;
                isElfNeedingHelp = true;
                isSantaAwake = true;
            }
            System.out.println("El "+Thread.currentThread().getName()+" necesita ayuda");
            randomTimeInSeconds = generator.nextInt(2);
            notifyAll();
            if (!isElfNeedingHelp){
                Thread.sleep(1000);
            }else{
                Thread.sleep(300);
            }
        }
    }



    public synchronized void llegoUnReno(int reinderIndex) throws InterruptedException{

        while (isReindeerNeedingHelp){
            wait();
        }

        if (isSantaAwake){
            Thread.sleep(300);
        }

        if (randomTimeInSeconds == 1){
            reinderThatArrive[reinderIndex] = true;
            numOfReindeerThatArrive++;

            if (numOfReindeerThatArrive == 9){
                isReindeerNeedingHelp = true;
            }
            randomTimeInSeconds = generator.nextInt(2);
            System.out.println(Thread.currentThread().getName()+" llega de sus vacaciones, enganchandose al trineo");
            notifyAll();
            Thread.sleep(1000);
        }
    }

    public synchronized void awakeSanta() throws InterruptedException{

        if (numOfReindeerThatArrive == 9){
            System.out.println();
           // Thread.sleep(1000);
            System.out.println("Santa sale a repartir los regalos!!");
            System.out.println();
            numOfReindeerThatArrive = 0;
            isReindeerNeedingHelp = false;
            Arrays.fill(reinderThatArrive, false);
            notifyAll();
        } else{
            if (isElfNeedingHelp){
                timesToHelpTheElf--;
                numOfElfThatNeedHelp -= 3;
                System.out.println("Santa ayuda a los duendes");
                System.out.println();
                if (timesToHelpTheElf == 0){
                    isElfNeedingHelp = false;
                }
                notifyAll();
            }else{

            }
        }
    }

    public synchronized void decrementTimesToHelpElves(){

    }

    public synchronized boolean isReindeerArrive(int index){
      return reinderThatArrive[index];
    }

    public synchronized int getRandomTimeInSeconds(){
        return randomTimeInSeconds;
    }
}
