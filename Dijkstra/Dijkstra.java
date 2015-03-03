public class MinDistance {

    private static int[][] adjMatrix;//邻接矩阵
    private static int numOfNodes;//节点数量

    private static int[] index;//用于存储最短路径下标的数组
    private static int[] shortPath;//用于存储到各点最短路径的权值和
    private static int[] result;//result[1]=1 表示已经求得顶点V0->V1的最短路径

    public static void init(){
        adjMatrix = new int[][]{
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

        numOfNodes = adjMatrix[0].length;
        index =  new int[numOfNodes];
        shortPath = new int[numOfNodes];
        result = new int[numOfNodes];
    }

    public static void dijstraMethod(){
        for(int i = 0; i < numOfNodes; i++){
            result[i] = 0;//全部初始化为未找到路径
            index[i] = 0;
            shortPath[i] = adjMatrix[0][i];//赋值为临接矩阵第一行权值
        }
        result[0] = 1;//V0到V0不需要求路径

        //开始主循环, 每次求得V0到顶点V的最短路径
        for(int i = 1; i < numOfNodes; i++){
            int min = 65535;
            int currentIndex = 0;
            for(int j = 0; j < numOfNodes; j++){
                if(result[j] != 1 && shortPath[j] < min){
                    currentIndex = j;
                    min = shortPath[j];
                }
            }
            result[currentIndex] = 1;//表示找到一个最近顶点

            //修正并更新当前最短路径和距离
            for(int k = 0; k < numOfNodes; k++){
                if(result[k] != 1 && adjMatrix[currentIndex][k]!=0 && (min + adjMatrix[currentIndex][k]) < shortPath[k]){
                    shortPath[k] = min + adjMatrix[currentIndex][k];//修改当前路径长度
                    index[currentIndex] = k; //存放前驱节点
                    //例如：0->1最短 我们要看 0->其他 最短还是 0->1->其他 距离最短, 来更新shortPath权重值
                }
            }
        }

        System.out.println("Shortest Distance: "+shortPath[numOfNodes-1]);//16

    }

    public static void main(String[] args){

        init();
        dijstraMethod();

    }
}
