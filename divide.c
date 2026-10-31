#include <stdio.h>

int main(void) {
    int a = 5;
    int b = 0;
    if (b == 0) {
        fprintf(stderr, "Error: Division by zero.\n");
        return 1;
    }
    float c = (float)a / (float)b;
    printf("%f\n", c);
    return 0;
}
