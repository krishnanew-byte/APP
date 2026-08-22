class Candidate{
int id;
String name;
int aptitude,technical,communication;
Candidate(int i,String n,int a,int t,int c){id=i;name=n;aptitude=a;technical=t;communication=c;}
int getTotal(){return aptitude+technical+communication;}
}
class Main{
public static void main(String[] args){
java.util.Scanner sc=new java.util.Scanner(System.in);
int N=sc.nextInt();
int K=sc.nextInt();
Candidate[] cands=new Candidate[N];
for(int i=0;i<N;i++){
int id=sc.nextInt();
String name=sc.next();
int a=sc.nextInt();
int t=sc.nextInt();
int com=sc.nextInt();
cands[i]=new Candidate(id,name,a,t,com);
}
java.util.Arrays.sort(cands,(x,y)->{
int ts=y.getTotal()-x.getTotal();
if(ts!=0)return ts;
return Integer.compare(x.id,y.id);
});
for(int i=0;i<K;i++){
System.out.println(cands[i].id+" "+cands[i].name+" "+cands[i].getTotal());
}
}
}