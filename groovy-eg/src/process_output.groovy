/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 12/24/13
 * Time: 7:53 AM
 * Example of process output handling.
 */

class LineOutput implements Runnable {
    private InputStream input
    private Closure closure

    LineOutput(InputStream input, Closure closure) {
        this.input = input
        this.closure = closure
    }

    public void run() {
        InputStreamReader isr = new InputStreamReader(input);
        BufferedReader br = new BufferedReader(isr);
        String next;
        try {
            while ((next = br.readLine()) != null) {
                closure.call(next)
            }
        } catch (IOException e) {
            throw new RuntimeException("exception while reading process stream", e);
        }
    }
}

// Simple
def proc = "ls -al".execute()
def rc = proc.waitFor()

println "rc = ${rc}"
println "out = ${proc.text}"

proc = "./test-shell.sh".execute()
// These methods work, but they wait until the process completes.
// proc.err.eachLine( { x -> println "err> ${x}"})
// proc.in.eachLine( { line -> println ">${line}"} )
Thread t = new Thread(new LineOutput(proc.inputStream,{ line -> println "out> ${line}"}))
t.start()
rc = proc.waitFor()
t.join()

println "rc = ${rc}"
