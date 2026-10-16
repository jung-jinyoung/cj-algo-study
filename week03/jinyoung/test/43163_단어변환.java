import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    
    public int solution(String begin, String target, String[] words) {
        // 1. target이 words에 존재하는지 확인        
        boolean targetExits = false; 
        for (String w : words) {
            if (w.equals(target)) {
                targetExits = true;
                break;
            }
        }
        // 2. target이 존재하지 않으면 0 return
        if (!targetExits) return 0;
        
        // 3. BFS를 위한 Queue와 방문 배열 초기화
        boolean[] visited = new boolean[words.length];
        
        // 4. begin과 한 글자만 다른 단어들을 Queue에 추가 
        // 문자열 값 비교가 아닌, 인덱스 값 비교로 방문 여부를 확인하기 위해 Queue에는 인덱스를 넣는다.
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0 ; i < words.length ; i++) {
            if (canChange(begin, words[i])) {
                visited[i] = true;
                queue.add(i);
            }
        }
        // 5. BFS 시작
        int count = 1; 
        
        while(!queue.isEmpty()) {
            int size = queue.size();
            // 6. 현재 Queue에 있는 단어들을 모두 확인
            for (int s = 0 ; s < size ; s++) {
                int currentIndex = queue.poll();
                String currentWord = words[currentIndex];
                
                // 7. 현재 단어가 target과 일치하면 count return
                if (currentWord.equals(target)) {
                    return count;
                }
                
                // 8. 현재 단어와 한 글자만 다른 단어들을 Queue에 추가
                for (int i = 0 ; i < words.length ; i++) {
                    if (!visited[i] && canChange(currentWord, words[i])) {
                        visited[i] = true;
                        queue.add(i);
                    }
                }
            }
            // 9. 한 번의 단어 변환이 끝났으므로 count 증가
            count++;
            
        }

        // 10. target에 도달하지 못했으면 0 return
        return 0;
        
    }
    
    // fromString -> toString 한 개의 알파벳을 바꿨을 때 일치할 수 있는가?
    private boolean canChange(String fs, String ts){
        int count = 0;
        for (int i = 0 ; i < fs.length(); i++) {
            if (fs.charAt(i) == ts.charAt(i)) {
                count++;
                continue;
            }
        }
        if (count == fs.length() - 1) return true;
        return false;
        
    }
}