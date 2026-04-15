// Program to find sizes of all orchards (connected 'T' regions)

public class Orchard {

    // Directions for 8 neighbors (horizontal, vertical, diagonal)
    static int[] rowDir = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] colDir = {-1, 0, 1, -1, 1, -1, 0, 1};

    // DFS function to count size of one orchard
    public static int dfs(char[][] grid, boolean[][] visited, int r, int c) {

        // Mark current cell as visited
        visited[r][c] = true;

        int count = 1; // Count current tree

        // Explore all 8 directions
        for (int i = 0; i < 8; i++) {
            int newRow = r + rowDir[i];
            int newCol = c + colDir[i];

            // If valid and not visited, continue DFS
            if (isValid(grid, visited, newRow, newCol)) {
                count += dfs(grid, visited, newRow, newCol);
            }
        }

        return count;
    }

    // Function to check if a cell is valid for DFS
    public static boolean isValid(char[][] grid, boolean[][] visited, int r, int c) {
        return (r >= 0 && r < grid.length &&           // inside row boundary
                c >= 0 && c < grid[0].length &&       // inside column boundary
                grid[r][c] == 'T' &&                  // must be a tree
                !visited[r][c]);                      // not visited
    }

    public static void main(String[] args) {

        /*
            Input Matrix:
            O T O O
            O T O T
            T T O T
            O T O T

            Expected Output: 5, 3
        */

        char[][] grid = {
                {'O','T','O','O'},
                {'O','T','O','T'},
                {'T','T','O','T'},
                {'O','T','O','T'}
        };

        boolean[][] visited = new boolean[grid.length][grid[0].length];

        System.out.print("Orchard sizes: ");

        // Traverse entire grid
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {

                // If tree and not visited → new orchard found
                if (grid[i][j] == 'T' && !visited[i][j]) {

                    // Find size using DFS
                    int size = dfs(grid, visited, i, j);

                    // Print orchard size
                    System.out.print(size + " ");
                }
            }
        }
    }
}