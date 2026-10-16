/*
   문제 유형: DFS(깊이 우선 탐색)로 시작 위치와 상하좌우로 연결된 같은 색상의 영역 변경
   문제4 : <LeetCode> - 733. Flood Fill
   난이도: Easy
*/

/*
   전략

   1. image는 각 칸에 색상 값이 저장된 2차원 배열이다.
   -> image[row][col] = 해당 위치의 색상 값
   2. sr과 sc는 색상을 변경하기 시작할 위치를 나타낸다.
   -> sr = 시작 행(Row)
   -> sc = 시작 열(Column)
   3. 시작 위치의 원래 색상을 originalColor에 저장한다.
   -> image[sr][sc]의 값
   4. 원래 색상과 변경할 색상 color가 같으면 image를 그대로 반환한다.
   -> 이미 같은 색상이므로 변경할 필요 없음
   -> DFS가 같은 칸을 반복 방문하는 문제 방지
   5. DFS 함수를 사용해 시작 위치부터 연결된 영역을 탐색한다.
   6. 현재 위치가 배열의 범위를 벗어났는지 확인한다.
   -> 행 또는 열이 배열의 범위를 벗어나면 탐색 종료
   7. 현재 위치의 색상이 originalColor와 같은지 확인한다.
   -> 색상이 다르면 변경 대상이 아니므로 탐색 종료
   8. 조건을 만족하면 현재 위치의 색상을 color로 변경한다.
   9. 현재 위치를 기준으로 상하좌우 네 방향을 DFS로 탐색한다.
   -> 위쪽 = row - 1
   -> 아래쪽 = row + 1
   -> 왼쪽 = col - 1
   -> 오른쪽 = col + 1
   10. 연결된 같은 색상의 모든 칸에 대해 이 과정을 반복한다.
   11. 모든 탐색이 끝나면 색상이 변경된 image 배열을 반환한다.
*/

class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        // 시작 위치의 원래 색상 저장
        int originalColor = image[sr][sc];
        // 원래 색상과 변경할 색상이 같으면 그대로 반환
        if (originalColor == color) {
            return image;
        }
        // 시작 위치부터 DFS 탐색
        dfs(image, sr, sc, originalColor, color);
        return image;
    }
    // row와 col은 현재 탐색 중인 행과 열의 위치
    private void dfs(int[][] image, int row, int col, int originalColor, int color) {
        // 현재 위치가 배열의 범위를 벗어나면 탐색 종료
        if (row < 0 || row >= image.length || col < 0 || col >= image[0].length) {
            return;
        }
        // 현재 칸의 색상이 원래 색상과 다르면 탐색 종료
        if (image[row][col] != originalColor) {
            return;
        }
        // 현재 칸의 색상을 새로운 색상으로 변경
        image[row][col] = color;
        // 현재 위치를 기준으로 상하좌우 탐색
        dfs(image, row - 1, col, originalColor, color);
        dfs(image, row + 1, col, originalColor, color);
        dfs(image, row, col - 1, originalColor, color);
        dfs(image, row, col + 1, originalColor, color);
    }
}