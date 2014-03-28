#include <mega128.h>
#include <delay.h>

void put(unsigned int data)
{
    while((UCSR0A & 0x20) == 0);
    UDR0 = data;  
    delay_ms(10);
    UDR0 = ('5');
    UCSR0A |= 0x20; 
}

void serial_init(void)
{
    #asm("cli");
    delay_ms(5);
    DDRE = 0x02;
    UCSR0A = 0;
    UCSR0B = 0b11011000;
    UCSR0C = 0b00000110;
    #asm("sei");
    UBRR0H = 0x00;
    UBRR0L = 103;
}

void main(void)
{
    serial_init();
    while(1){
        put(0x02);
        delay_ms(1000);
    }
}



interrupt [USART0_RXC] void uart_rcv(void)
{
    unsigned int data;    
    data = UDR0;
    delay_ms(1);
    put(data); 
}
