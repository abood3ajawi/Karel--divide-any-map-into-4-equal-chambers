/*
 * File: BlankKarel.java
 * ---------------------
 * This class is a blank one that you can change at will.
 */

import stanford.karel.*;

public class BlankKarel extends SuperKarel {
	public void safe(){
	while (true)
	{
		if(frontIsBlocked())
			turnLeft();
		else
			break;
	}
	}
	private void diagonal() {
		while (true)
		{
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
		safe();
	}
	private void line() {
		while(true)
		{
			if(frontIsBlocked())
				break;
			move();
		}
		safe();
	}
	public int count(){
     int count = 1;
		while (true)
		{
		 if(frontIsBlocked())
			 break;
			else {
				move();
				System.out.println(count);
				count++;
			}
		}
      turnLeft();
		return count;
	}
	public void goMiddle(int start,int distance){
		for (int i = start ; i<(distance/2) ;i++)
		{
			move();
		}
		turnLeft();
	}
	public void putBeeper(boolean up,int height,int wORh){
		for (int i = 1 ; i<height ;i++)
		{
			putBeeper();
			move();
		}
		putBeeper();
		if(up && wORh==1) {
			turnLeft();
			move();
			turnLeft();
		}
		else if(up) {
			turnLeft();
			turnLeft();
			turnLeft();
			move();
			turnLeft();
			turnLeft();
			turnLeft();
		}
	}
	public void putBeeperOneD(int distance){
		safe();
		int temp =distance;

		while (true)
		{
			System.out.println(temp+"height");
			int modules = temp>6? 4 : 2;
			if((temp-3)%modules == 0)
			{

				int counter = 0;

				int d = ((temp-3)/4)>=1?((temp-3)/4):1;
				System.out.println(d);
				for (int i =0;i<temp;i++) {
					if(frontIsBlocked())
						break;
					if( d==counter)
					{
						putBeeper();
						counter=0;
					}
					else
					{
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
	public void run() {
		int width = count();
		int height = count();
		System.out.println(height+","+width);
		if(((height ==1 || height ==2)&&(width ==1))||((height ==1)&&(width ==1 || width==2))){
			System.out.println("can not be chamber");
		}
		else if((height == 2) && (width ==2)){
			diagonal();
		}
		else if(height ==1 || height ==2) {
			putBeeperOneD(width);
		}
		else if(width ==1 || width==2){
			putBeeperOneD(height);
		}
		else if((height == width) && (height%2==0)) {
			System.out.println(height + "Vvvvv");
			diagonal();
		line();
		diagonal();
	  }
		else {
			goMiddle(0,width);
			if(width%2 ==0)
			putBeeper(true,height,1);
			putBeeper(false,height,1);
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
			///
			if(height%2 == 1 || width%2==0)
				goMiddle(0,height);
			else
				goMiddle(1,height);
			///
			if(width%2 ==0) {
				turnLeft();
				turnLeft();
			}
			if(height%2 ==0)
			putBeeper(true,width,0);
			putBeeper(false,width,0);
		}
	}

}

