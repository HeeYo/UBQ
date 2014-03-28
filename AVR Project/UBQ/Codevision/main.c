#include<mega128.h>
#include<delay.h>
                          
void send(int data)
{
    while(!(UCSR0A & 0x20))
    UDR0 = data;
    UCSR0A |= 0x20;    
}

void main()
{
    DDRE = 0xfe;
    
    UCSR0A = 0x00;
    UCSR0B = 0b01011000;
    UCSR0C = 0b00000110;  
    
    #asm("sei");      //전역 인터럽트 허용
    
    UBRR0H = 0;
    UBRR0L = 103; 
    
    while(1)
    {
        send(0xff);
        delay_ms(2000);
        send(0x00);
        delay_ms(2000);       
    }  

}