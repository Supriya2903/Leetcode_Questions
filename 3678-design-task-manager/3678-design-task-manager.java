import java.util.*;

class TaskManager {
    private static class Task {
        int userId, taskId, priority;
        Task(int u, int t, int p) {
            userId = u; taskId = t; priority = p;
        }
    }

    // Map to store current valid task info
    private Map<Integer, Task> taskMap;

    // Max Heap: order by priority DESC, then taskId DESC
    private PriorityQueue<Task> maxHeap;

    public TaskManager(List<List<Integer>> tasks) {
        taskMap = new HashMap<>();
        maxHeap = new PriorityQueue<>((a, b) -> {
            if (a.priority != b.priority) return b.priority - a.priority;
            return b.taskId - a.taskId;
        });

        for (List<Integer> t : tasks) {
            int userId = t.get(0), taskId = t.get(1), priority = t.get(2);
            Task task = new Task(userId, taskId, priority);
            taskMap.put(taskId, task);
            maxHeap.offer(task);
        }
    }

    public void add(int userId, int taskId, int priority) {
        Task task = new Task(userId, taskId, priority);
        taskMap.put(taskId, task);
        maxHeap.offer(task);
    }

    public void edit(int taskId, int newPriority) {
        Task old = taskMap.get(taskId);
        Task updated = new Task(old.userId, taskId, newPriority);
        taskMap.put(taskId, updated);
        maxHeap.offer(updated);
    }

    public void rmv(int taskId) {
        taskMap.remove(taskId);
    }

    public int execTop() {
        while (!maxHeap.isEmpty()) {
            Task top = maxHeap.poll();
            Task current = taskMap.get(top.taskId);
            // ✅ check userId and priority both
            if (current != null && current.priority == top.priority && current.userId == top.userId) {
                taskMap.remove(top.taskId);
                return top.userId;
            }
        }
        return -1;
    }
}
