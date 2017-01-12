import java.util.*;

/**
 * Created by swati on 1/5/16.
 */
public class GraphTraversal {

    private Scanner in;
    private int vertices;
    private int adj[][];
    private int visited[];
    Stack<Integer> stack;
    private boolean cycle;
    private static int INF = Integer.MAX_VALUE;

    public GraphTraversal(int n, int[][]adj) {

        this.in = new Scanner(System.in);
        this.vertices = n;
        this.adj = adj;
        this.visited = new int[n];
        this.stack = new Stack<Integer>();
        this.cycle = false;
    }

    private void dfsTraversal(int root) {

        System.out.println(root);
        this.visited[root] = 1;
        for (int i = 0; i < this.vertices; i++) {
            if (this.adj[root][i] == 1 && this.visited[i] == 0) {
                dfsTraversal(i);
            }
        }
    }

    public void dfs(int start) {
        dfsTraversal(start);
        for (int i = 0; i < this.vertices; i++) {
            if (this.visited[i] == 0) {
                dfsTraversal(i);
            }
        }
    }

    private void bfsTraversal(Queue<Integer> queue) {

        if (queue.isEmpty()) {
            return;
        }
        int current = queue.remove();
        System.out.println(current);
        for (int i = 0; i < this.vertices; i++) {
            if (this.adj[current][i] == 1 && this.visited[i] == 0) {
                queue.add(i);
                this.visited[i] = 1;
            }
        }
        bfsTraversal(queue);
    }

    private void topoTraversal(int root) {

        this.visited[root] = 1;

        for (int i = 0; i < this.vertices; i++) {
            if (this.adj[root][i] == 1 && this.visited[i] == 0) {
                topoTraversal(i);
            }
        }
        this.stack.push(root);
    }

    public void bfs(int start) {
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(start);
        this.visited[start] = 1;
        bfsTraversal(q);
    }

    public Stack<Integer> topo(int start) {
        for (int i = 0; i < this.vertices; i++) {
            if (this.visited[i] == 0) {
                topoTraversal(i);
            }
        }

        return this.stack;
        	
    }

    public void detect(int v) {
        this.visited[v] = 1;
        System.out.println("adding... " + v);
        this.stack.push(v);

        for (int i = 0; i < this.vertices; i++) {
            if (this.adj[v][i] == 1) {
            	System.out.println("processing... " + v + " -> " + i);
                if (this.stack.contains(i)) {
                    this.cycle = true;
                } else if (this.visited[i] == 0) {
                    detect(i);
                }
            }
        }
        System.out.println("removing... " + v);
        this.stack.removeElement(v);
    }

    public boolean detectCycle() {
        detect(this.adj[0][0]);
        return this.cycle;
    }

    public void shortestPath(int s) {
        System.out.println("Enter the weights");
        int weight[][] = new int[this.vertices][this.vertices];
        for (int i = 0; i < this.vertices; i++) {
            for (int j = 0; j < this.vertices; j++) {
                int a = this.in.nextInt();
                if (a == 0) {
                    a = INF;

                }
                weight[i][j] = a;
            }
        }
        Stack<Integer> stack = topo(s);
        int[] distance = new int[this.vertices];
        for (int i = 0; i < this.vertices; i++) {
            distance[i] = INF;
        }
        distance[s] = 0;

        while (!stack.isEmpty()) {
            int u = stack.pop();

            if (distance[u] != INF) {
                for (int v = 0; v < this.vertices; v++) {
                    if (weight[u][v] != INF && distance[v] > distance[u] + weight[u][v]) {
                        distance[v] = distance[u] + weight[u][v];
                    }
                }
            }
        }

        System.out.println("Shortest Path from " + s + " ");
        for (int i = 0; i < this.vertices; i++) {
            System.out.println(i + ": " + distance[i]);
        }

    }
    
    private boolean consider(int[] row, int[] col, int i, int j, int c, int[][] visit) {
    	if (visit[i + row[c]][j + col[c]] == 0) {
	    	return (this.adj[i + row[c]][j + col[c]] == 1);
    	}
    	return false;
    }
    
    private void dfsIsland(int i, int j, int[][] visit) {
  
    	visit[i][j] = 1;
    	int[] row = {-1, -1, -1, 0, 0, 1, 1, 1};
    	int[] col = {-1, 0, 1, -1, 1, -1, 0, 1};
    	
    	for (int c = 0; c < 8; c++) {
    		if (i + row[c] != -1 && j + col[c] != -1 && i + row[c] != this.vertices && j + col[c] != this.vertices) {
    			if (consider(row, col, i, j, c, visit)) {    			
    				dfsIsland(i + row[c], j + col[c], visit);
    			}
    		}
    	}
    }
    
    private int numIsland() {
    	int numIsland = 0;
    	int visit[][] = new int[this.vertices][this.vertices];
  
    	for (int i = 0; i < this.vertices; i++) {
    		for (int j = 0; j < this.vertices; j++) {
    			if (this.adj[i][j] == 1 && visit[i][j] != 1) {
    				System.out.println("[" + i + "][" + j + "]");
    				dfsIsland(i, j, visit);
    				numIsland += 1;
    			}
    		}
    	}
    	return numIsland;
    }

    public static void main(String args[]) {

        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("Sorting Algos");
            System.out.println("1. DFS");
            System.out.println("2. BFS");
            System.out.println("3. Topological Sort");
            System.out.println("4. Detect Cycles");
            System.out.println("5. Shortest Path");
            System.out.println("6. Connected Components in a graph");
            System.out.println("Q. Exit");
            System.out.println("Enter your choice: ");

            String ch = in.next();

            System.out.println("Enter the graph");
            System.out.println("No of vertices: ");
            int n = in.nextInt();
            System.out.println("Enter the adjacancy matrix");
            int adj[][] = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    adj[i][j] = in.nextInt();
                }
            }

            GraphTraversal g = new GraphTraversal(n, adj);

            if (ch.equals("1")) {
                System.out.println("Enter the starting point: ");
                g.dfs(in.nextInt());
            } else if (ch.equals("2")) {
                System.out.println("Enter the starting point: ");
                g.bfs(in.nextInt());
            } else if (ch.equals("3")) {
                System.out.println("Enter the starting point: ");
                Stack<Integer> stack = g.topo(in.nextInt());
                while(!stack.isEmpty()) {
                    System.out.println(stack.pop());
                }
            } else if (ch.equals("4")) {
                if (g.detectCycle()) {
                    System.out.println("Yes cycles");
                } else {
                    System.out.println("No cycles");
                }
            } else if (ch.equals("5")) {
                System.out.println("Enter the starting point: ");
                g.shortestPath(in.nextInt());
            } else if (ch.equals("6")) {
                System.out.println("The number of islands are: ");
                System.out.println(g.numIsland());
            } else {
                System.exit(0);
            }
        }
    }
}
