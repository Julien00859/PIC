#include <main.h>

int toBCD(int binary) {
   if (binary >= 0 && binary < 100)
      return ((binary / 10) << 4) + binary % 10;
   else if (binary < 0)
      return 0;
   else
      return 99;
}

void main() {
   output_c(0b11);
   output_d(toBCD(15));
}
