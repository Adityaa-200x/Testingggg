#include <stdio.h>

int main(void) {
    int p = 5;
    int q = 0;
    if (q == 0) {
        fprintf(stderr, "Error: Division by zero.\n");
        return 1;
    }
    float c = (float)p / (float)q;
    printf("%f\n", c);
    return 0;
}
