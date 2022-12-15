import stanford.karel.*;
public class Homework extends SuperKarel {
    public void safeDirection(){
        while (true) {
            if(frontIsBlocked())
                turnLeft();
            else
                break;
        }
    }
    public int  calculateDistance(){
        int count = 1;
        while (true) {
            if(frontIsBlocked())
                break;
            else {
                move();
                count++;
            }
        }
        turnLeft();
        return count;
    }
    public void goMiddle(int start,int distance){
        for (int i = start ; i<(distance/2) ;i++){
            move();
        }
        turnLeft();
    }
    public void goStraightLine() {
        while(true){
            if(frontIsBlocked())
                break;
            move();
        }
        safeDirection();
    }
    public void putBeeperDiagonal() {
        while (true) {
            if(frontIsBlocked() && leftIsBlocked()) {
                putBeeper();
                break;
            }
            putBeeper();
            move();
            turnLeft();
            move();
            turnLeft();
            turnLeft();
            turnLeft();

        }
        safeDirection();
    }
    public void putBeeperLine(boolean upDown,int distance,boolean widthHeight){
        for (int i = 1 ; i<distance ;i++) {
            putBeeper();
            move();
        }
        putBeeper();
        if(upDown && widthHeight) {
            turnLeft();
            move();
            turnLeft();
        }
        else if(upDown) {
            turnLeft();
            turnLeft();
            turnLeft();
            move();
            turnLeft();
            turnLeft();
            turnLeft();
        }
    }
    public void putBeeperMap1xLength(int distance){
        safeDirection();
        int temp =distance;
        while (true)
        {
            int modules = temp>6? 4 : 2;
            if((temp-3)%modules == 0)
            {
                safeDirection();

                int counter = 0;

                int d = ((temp-3)/4)>=1?((temp-3)/4):1;
                for (int i =0;i<temp;i++) {
                    if(frontIsBlocked())
                        break;
                    if( d==counter) {
                        putBeeper();
                        counter=0;
                    }
                    else {
                        counter++;
                    }
                    move();

                }
                break;
            }
            putBeeper();
            move();
            temp--;
        }
    }
    public void putBeeperMap2xHeight(int distance){
        boolean toggle = false;
        boolean touch = true;
        int modules = distance>6? 4 : 2;
        while (true) {
            if ((distance - 3) % modules == 0) {

                int counter = 0;
                int d = ((distance - 3) / 4) >= 1 ? ((distance - 3) / 4) : 1;
                for (int i = 0; i < distance; i++) {
                    if (touch) {
                        move();
                        turnLeft();
                        counter++;
                        touch =false;
                    }
                    else if (d == counter) {
                        if (toggle) {
                            putBeeperSegmentToLeft();

                        } else {
                            putBeeperSegmentToRight();
                        }
                        toggle = !toggle;
                        counter = 0;
                    } else {
                        counter++;
                        move();

                    }
                }
                break;
            }
            if(touch){
                System.out.println("Sss");
                putBeeper();
                move();
                putBeeper();
                turnLeft();
                touch =false;
            }
            else {
                System.out.println("Sss222");
                if (toggle) {
                    putBeeperSegmentToLeft();
                } else {
                    putBeeperSegmentToRight();
                }
                toggle = !toggle;
            }
            distance--;
        }

    }
    public void putBeeperMap2xWidth(int distance){
        boolean toggle = false;
        boolean touch = true;
        int modules = distance>6? 4 : 2;
        while (true) {
            if ((distance - 3) % modules == 0) {

                int counter = 0;
                int d = ((distance - 3) / 4) >= 1 ? ((distance - 3) / 4) : 1;
                for (int i = 0; i < distance; i++) {
                    if (touch) {
                        turnLeft();
                        move();
                        turnLeft();
                        turnLeft();
                        turnLeft();
                        counter++;
                        touch =false;
                    }
                    else if (d == counter) {
                        if (toggle) {
                            putBeeperSegmentToDown();

                        } else {
                            putBeeperSegmenttoUp();
                        }
                        toggle = !toggle;
                        counter = 0;
                    } else {
                        counter++;
                        move();

                    }
                }
                break;
            }
            if(touch){
                System.out.println("done");
                turnLeft();
                putBeeper();
                move();
                putBeeper();
                turnLeft();
                turnLeft();
                turnLeft();
                touch =false;
            }
            else {
                System.out.println("Sss222");
                if (toggle){
                    putBeeperSegmentToDown();
                } else {
                    putBeeperSegmenttoUp();
                }
                toggle = !toggle;
            }
            distance--;
        }

    }
    public void putBeeperSegmentToRight(){
        System.out.println("toRight();");
        move();
        putBeeper();
        turnLeft();
        move();
        putBeeper();
        turnLeft();
        turnLeft();
        turnLeft();

    }
    public void putBeeperSegmentToLeft(){
        System.out.println("toLeft();");
        move();
        putBeeper();
        turnLeft();
        turnLeft();
        turnLeft();
        move();
        putBeeper();
        turnLeft();

    }
    public void putBeeperSegmentToDown(){
        move();
     turnLeft();
     putBeeper();
     move();
     putBeeper();
     turnLeft();
     turnLeft();
     turnLeft();
    }
    public void putBeeperSegmenttoUp(){
    move();
    turnLeft();
    turnLeft();
    turnLeft();
    putBeeper();
    move();
    putBeeper();
    turnLeft();
    }


    public void run() {
        setBeepersInBag(10000);
        int width = calculateDistance();
        int height = calculateDistance();
        if(((height ==1 || height ==2)&&(width ==1))||((height ==1)&&(width ==1 || width==2))){
            System.out.println("can't be divided");
        }
        else if((height == 2) && (width ==2)){
            putBeeperDiagonal();
        }
        else if(height == 2) {
            putBeeperMap2xWidth(width);
        }
        else if(height ==1) {
            putBeeperMap1xLength(width);
        }
        else if(width ==2){
            putBeeperMap2xHeight(height);
        }
        else if(width ==1){
            putBeeperMap1xLength(height);
        }
        else if((height == width) && (height%2==0)) {
            putBeeperDiagonal();
            goStraightLine();
            putBeeperDiagonal();
        }
        else {
            goMiddle(0,width);
            if(width%2 ==0)
                putBeeperLine(true,height,true);
            putBeeperLine(false,height,true);
            turnLeft();
            if((width%2)==0) {
                turnLeft();
                turnLeft();
            }
            if(width%2 ==0) {
                goMiddle(1, width);
                turnLeft();
                turnLeft();
            }
            else
                goMiddle(0,width);
            if(height%2 == 1 || width%2==0)
                goMiddle(0,height);
            else
                goMiddle(1,height);
            if(width%2 ==0) {
                turnLeft();
                turnLeft();
            }
            if(height%2 ==0)
                putBeeperLine(true,width,false);
            putBeeperLine(false,width,false);

        }
    }
}
    