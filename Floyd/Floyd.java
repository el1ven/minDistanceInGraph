public class Floyd{

    private static int[][] D;//D代表顶点与顶点的最短路径权值和的矩阵
    private static int [][] P;//P代表对应顶点的最短路径的前驱矩阵
    private static int numOfNodes;

    public static void init(){
        //完成数据初始化
        D = new int[][]{
                {0, 1, 5, 65535, 65535, 65535, 65535, 65535, 65535},
                {1, 0, 3, 7, 5, 65535, 65535, 65535, 65535},
                {5, 3, 0, 65535, 1, 7, 65535, 65535, 65535},
                {65535, 7, 65535, 0, 2, 65535, 3, 65535, 65535},
                {65535, 5, 1, 2, 0, 3, 6, 9, 65535},
                {65535, 65535, 7, 65535, 3, 0, 65535, 5, 65535},
                {65535, 65535, 65535, 3, 6, 65535, 0, 2, 7},
                {65535, 65535, 65535, 65535, 9, 5, 2, 0, 4},
                {65535, 65535, 65535, 65535, 65535, 65535, 7, 4, 0}
        };
        numOfNodes = D[0].length;
        P = new int[numOfNodes][numOfNodes];
        for(int i = 0; i < numOfNodes; i++){
            for(int j = 0; j< numOfNodes; j++){
                P[i][j] = j;
            }
        }
    }
    public static void floydMethod(){

        for(int i = 0; i < numOfNodes; i++){
            for(int j = 0; j < numOfNodes; j++) {
                for(int k = 0; k < numOfNodes; k++){
                    if(D[j][k] > D[j][i] + D[i][k]){
                        //如果0->1->2 < 0->2
                        D[j][k] = D[j][i] + D[i][k];//更新最短路径
                        P[j][k] = P[j][i];//更新前驱
                    }
                }
            }
        }
    }

    public static void printP(){
        for(int i = 0; i < numOfNodes; i++){
            for(int j = 0; j < numOfNodes; j++){
                System.out.print(P[i][j]+" ");
            }
            System.out.println("");
        }
    }

    public static void printResult(){

        for(int i = 0; i < numOfNodes; i++){
            for(int j = i+1; j < numOfNodes; j++){
                System.out.print(i+"->"+j+" weight: "+D[i][j]+" Path: "+i);//i->j的最短路径
                int k = P[i][j];//求他们的前驱节点
                while(k != j){//一直求先驱打印
                    System.out.print("->"+k+" ");
                    k = P[k][j];//求j,k的先驱节点
                }
                System.out.print("->"+j+"\n");//打印Path上的最后一个节点j
            }
            System.out.println("");//换行
        }

    }

    public static void main(String[] args){

        init();
        floydMethod();
        //printP();
        printResult();

    }
}
