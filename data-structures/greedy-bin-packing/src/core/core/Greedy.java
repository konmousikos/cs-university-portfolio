package core;

import java.io.*;


public class Greedy {
    public static void main(String[] args) throws IOException {

        if (args.length == 0) {
            System.out.println("Usage: java core.Greedy <input_file>");
            return;
        }

        try {

            File file = new File(args[0]);
            BufferedReader br = new BufferedReader(new FileReader(file));
            MaxPQ diskoi=new MaxPQ();
            String st;


            int[] pinakas=new int[0];
            while ((st = br.readLine()) != null){
                //check if max is 1TB
                int memory=Integer.parseInt(st);
                if (memory>1000000){
                    System.out.println("Memory need must be less than 1TB");
                    System.exit(0);
                }else{
                    int[] temppinakas=new int[pinakas.length+1];
                    System.arraycopy(pinakas, 0, temppinakas, 0, pinakas.length);
                    pinakas=temppinakas;
                    pinakas[pinakas.length-1]=memory;
                }
            }


            int metrhthskey=0;
            for (int i=0;i<pinakas.length;i++){

                if (diskoi.size()>0 && diskoi.peek().getFreeSpace()>=pinakas[i]){
                    Disk temp=diskoi.getMax();
                    temp.addFolder(pinakas[i]);
                    diskoi.add(temp);
                }else{
                    Disk temp=new Disk(metrhthskey);
                    temp.addFolder(pinakas[i]);
                    diskoi.add(temp);
                    metrhthskey++;
                }
            }


            long sum = 0;
            MaxPQ tempPQ = new MaxPQ();

            while (diskoi.size() > 0) {
                Disk d = diskoi.getMax();
                sum += (1000000 - d.getFreeSpace());
                tempPQ.add(d);
            }

            diskoi = tempPQ;

            System.out.println("Sum of all folders =  "+(sum/1000000)+" TB ");
            System.out.println("Total number of disks used = " + (diskoi.size()));
            if (pinakas.length <=100){
                int range=diskoi.size();
                for (int i=1;i<=range;i++){
                    System.out.print("id " + diskoi.peek().getId() + "   " + diskoi.peek().getFreeSpace() + " : ");
                    diskoi.getMax().printFolders(System.out);
                    System.out.println();
                }
            }else{
                for (int i=0;i<diskoi.size();i++){
                    System.out.print("id " + diskoi.peek().getId() + "   " + diskoi.getMax().getFreeSpace() );
                    System.out.println();
                }
            }


            pinakas= Sort.mergesort(pinakas,0,(pinakas.length-1));
            System.out.println("Sorted");
            MaxPQ newdiskoi=new MaxPQ();
            diskoi=newdiskoi;
            metrhthskey=0;
            for (int i=0;i<pinakas.length;i++){
                if (diskoi.size()!=0 && diskoi.peek().getFreeSpace()>=pinakas[i]){
                    Disk temp=diskoi.getMax();
                    temp.addFolder(pinakas[i]);
                    diskoi.add(temp);
                }else{
                    Disk temp=new Disk(metrhthskey);
                    temp.addFolder(pinakas[i]);
                    diskoi.add(temp);
                    metrhthskey++;
                }
            }



            while (diskoi.size() > 0) {
                Disk d = diskoi.getMax();
                sum += (1000000 - d.getFreeSpace());
                tempPQ.add(d);
            }

            diskoi = tempPQ;

            System.out.println("Sum of all folders =  "+(sum/1000000)+" TB ");
            System.out.println("Total number of disks used = " + (diskoi.size()));
            if (pinakas.length <=100){
                int range=diskoi.size();
                for (int i=1;i<=range;i++){
                    System.out.print("id " + diskoi.peek().getId() + "   " + diskoi.peek().getFreeSpace() + " : ");
                    diskoi.getMax().printFolders(System.out);
                    System.out.println();
                }
            }else{
                for (int i=0;i< diskoi.size();i++){
                    System.out.print("id " + diskoi.peek().getId() + "   " + diskoi.getMax().getFreeSpace() );
                    System.out.println();
                }
            }

        }catch(FileNotFoundException e){
            throw new FileNotFoundException();
        }
    }

}
