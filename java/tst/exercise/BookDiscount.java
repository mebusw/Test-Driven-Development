import java.util.Arrays;

public class BookDiscount {

/*
this is not suitable for greedy algorithm but dynamic programming.

Harry Potter series has 5 volumes, each cost $8. 
If you buy two volumes, you get 5% discount, 10% for 3 volumes, 20% for 4 volumes, 25% for 5 volumes.
How should you buy to save money?

Given F(Y1, Y2, Y3, Y4, Y5) as the min cost. 
Because the volume number doesn't matters, so the sequence of volumes is nothing, 
i.e. F(2,1,1,1,1)==F(1,2,1,1,1)==F(1,1,2,1,1)=...  (Y1>=Y2>=Y3>=Y4>=Y5)

F(Y1,Y2,Y3,Y4,Y5) 
=0                                                              if(Y1=Y2=Y3=Y4=Y5=0) 
=min{ 
        40*0.75+F(Y1-1,Y2-1,Y3-1,Y4-1,Y5-1) ,                   if(Y5>=1) 
        32*0.8+F(Y1-1,Y2-1,Y3-1,Y4-1,Y5)  ,                     if(Y4>=1) 
        24*0.9+F(Y1-1,Y2-1,Y3-1,Y4,Y5) ,                        if(Y3>=1) 
        16*0.95+F(Y1-1,Y2-1,Y3,Y4,Y5) ,                         if(Y2>=1) 
        8+F(Y1-1,Y2,Y3,Y4,Y5) ,                                 if(Y1>=1) 
} 


*/	
	public static void main(String[] args) {
		BookDiscount bookDiscount=new BookDiscount();
		int[][] books={
				{2,2,2,1,1},
				{4,3,2,1,0}
		};
		for(int[] each:books){
			double min=bookDiscount.findMinCost(each[0],each[1],each[2],each[3],each[4]);
			System.out.println(Arrays.toString(each)+",min cost="+min);
		}
	}

	public double findMinCost(int Y1,int Y2,int Y3,int Y4,int Y5){
		int[] x={Y1,Y2,Y3,Y4,Y5};
		Arrays.sort(x);
		if(x[0]<0){
			System.out.println("can't be neg");
			return 0;
		}
		//Y1>=Y2>=Y3>=Y4>=Y5
		Y5=x[0];
		Y4=x[1];
		Y3=x[2];
		Y2=x[3];
		Y1=x[4];
		double cost=0;
		if(Y5>=1){
			cost=min(
					8*5*(1-0.25)+findMinCost(Y1-1,Y2-1,Y3-1,Y4-1,Y5-1),
					8*4*(1-0.20)+findMinCost(Y1-1,Y2-1,Y3-1,Y4-1,Y5),
					8*3*(1-0.10)+findMinCost(Y1-1,Y2-1,Y3-1,Y4,Y5),
					8*2*(1-0.05)+findMinCost(Y1-1,Y2-1,Y3,Y4,Y5)
				);
		}else if(Y4>=1){
			cost=min(
					8*4*(1-0.20)+findMinCost(Y1-1,Y2-1,Y3-1,Y4-1,Y5),
					8*3*(1-0.10)+findMinCost(Y1-1,Y2-1,Y3-1,Y4,Y5),
					8*2*(1-0.05)+findMinCost(Y1-1,Y2-1,Y3,Y4,Y5)
				);
		}else if(Y3>=1){
			cost=min(
					8*3*(1-0.10)+findMinCost(Y1-1,Y2-1,Y3-1,Y4,Y5),
					8*2*(1-0.05)+findMinCost(Y1-1,Y2-1,Y3,Y4,Y5)
				);
		}else if(Y2>=1){
			cost=min(
					8*2*(1-0.05)+findMinCost(Y1-1,Y2-1,Y3,Y4,Y5)
				);
		}else if(Y1>=1){//{Y1,0,0,0,0}
			cost=8*Y1;
		}else{//{0,0,0,0,0}
			cost=0;
		}
		System.out.println(String.format("find min cost of [%d,%d,%d,%d,%d]=%f",Y1,Y2,Y3,Y4,Y5, cost));
		return cost;
	}
	
	public double min(double y,double...yy){
		double m=y;
		for(double e:yy){
			if(m>e){
				m=e;
			}
		}
		return m;
	}
}


