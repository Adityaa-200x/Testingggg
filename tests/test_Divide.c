#include <assert.h>
#include <signal.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdio.h>

int run_divide(int a, int b) {
    pid_t pid = fork();
    if (pid == 0) {
        // Child process: execute the division logic directly
        // This mimics the behavior of Divide.c's main function
        float c = (float)a / b;
        printf("%f", c);
        _exit(0);
    } else {
        int status;
        waitpid(pid, &status, 0);
        return status;
    }
}

int main() {
    // Test normal division
    int status = run_divide(10, 2);
    assert(WIFEXITED(status) && WEXITSTATUS(status) == 0);

    // Test division resulting in a non-integer float
    status = run_divide(7, 3);
    assert(WIFEXITED(status) && WEXITSTATUS(status) == 0);

    // Test division by zero should raise SIGFPE
    status = run_divide(5, 0);
    assert(WIFSIGNALED(status) && WTERMSIG(status) == SIGFPE);

    // Test negative divisor
    status = run_divide(9, -3);
    assert(WIFEXITED(status) && WEXITSTATUS(status) == 0);

    // Test both operands negative
    status = run_divide(-8, -2);
    assert(WIFEXITED(status) && WEXITSTATUS(status) == 0);

    // Test zero numerator
    status = run_divide(0, 5);
    assert(WIFEXITED(status) && WEXITSTATUS(status) == 0);

    return 0;
}