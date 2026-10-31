#include <stdio.h>

int main(void) {
    int a = 5;
    int b = 2;

    if (b == 0) {
        fprintf(stderr, "Error: division by zero\n");
        return 1;
    }

    float c = (float)a / (float)b; // ensure floating‑point division
    printf("%f\n", c);
    return 0;
}
