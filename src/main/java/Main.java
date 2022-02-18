import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        ExecutorService executorService =
                new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<Runnable>());

        Runnable runnableTask = () -> {
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Callable<String> callableTask = () -> {
            System.out.println("callable task start time: " + LocalDateTime.now());
            TimeUnit.MILLISECONDS.sleep(300);
            System.out.println("callable task stop time: " + LocalDateTime.now());
            return "Task's execution";
        };

        List<Callable<String>> callableTasks = new ArrayList<>();
        callableTasks.add(callableTask);
        callableTasks.add(callableTask);
        callableTasks.add(callableTask);

//        The execute() method is void and doesn't give any
//        possibility to get the result of a task's execution
//        or to check the task's status (is it running):

//        executorService.execute(runnableTask);

//        submit() submits a Callable or a Runnable task to an
//        ExecutorService and returns a result of type Future:

//        Future<String> future = executorService.submit(callableTask);

//        invokeAny() assigns a collection of tasks to
//        an ExecutorService, causing each to run,
//        and returns the result of a successful execution of one
//        task (if there was a successful execution):

        String result = executorService.invokeAny(callableTasks);

//        invokeAll() assigns a collection of tasks to
//        an ExecutorService, causing each to run, and returns
//        the result of all task executions in the form of a
//        list of objects of type Future:

//        List<Future<String>> futures = executorService.invokeAll(callableTasks);

//        The shutdown() method doesn't cause immediate destruction of
//        the ExecutorService. It will make the ExecutorService
//        stop accepting new tasks and shut down after all running
//        threads finish their current work:

        System.out.println(result);
        executorService.shutdown();
    }
}
