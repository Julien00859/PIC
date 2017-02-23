#include <18F458.h>
#device ADC=10
#use delay(crystal=20000000)

#define BCD_UNITS(x) (x % 10)
#define BCD_TENS(x) (x / 10)
void gestionLed(int temp, int seuil);
void affTemp(int temperature);
void main()
{
   setup_adc_ports(AN0);
   setup_adc(ADC_CLOCK_INTERNAL);
   setup_low_volt_detect(FALSE);

   int seuil = 35;
   int temperature;

   while(TRUE)
   {
      temperature = read_adc() * 100 / 1024;
      gestionLed(temperature,seuil); 
      affTemp(temperature);
   }
}

void gestionLed(int temp, int seuil){
     if(temp > seuil){
      output_bit(PIN_C1,1);
      output_bit(PIN_C0,0);
      
   }
   else{
      output_bit(PIN_C1,0);
      output_bit(PIN_C0,1);
   }

}
void affTemp(int temperature){
      output_d(BCD_UNITS(temperature) | (1 << 4));
      delay_ms(10);
      output_d(BCD_TENS(temperature) | (1 << 5));
      delay_ms(10);
}


