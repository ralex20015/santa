public class Main {
    public static void main(String[] args) {

        Buffer buffer = new Buffer();
        Santa san = new Santa(buffer);
        Thread santa = new Thread(san,"Santa");
        santa.start();
        santa.setPriority(10);
        Thread []renos = new Thread[9];
        Thread []duendes = new Thread[9];
        for (int i = 1; i <= renos.length; i++) {
            renos[i-1] = new Thread(new Reno(buffer,i-1),"Reno "+i);
            duendes[i-1] = new Thread(new Duende(buffer,"Duende "+i),"Duende "+i);
        }
        for (Thread reno : renos) {
            reno.start();
        }

        for (Thread duende: duendes){
            duende.start();
        }

    }
}