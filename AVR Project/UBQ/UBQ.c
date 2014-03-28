#include <avr/io.h>
#include <util/delay.h>

void putch(unsigned char data)
{
    while(!(UCSR0A & 0x20));
    UDR0 = data;
}

void main(void)
{
    DDRE = 0x02;
    
    UCSR0A = 0x00;
    UCSR0B = 0x98; // Rx Interrupt Enable, Rx,Tx Enable
    UCSR0C = 0x06; // 8bit, No parity, 1bit stop
    
    #asm("sei"); // Global interrupt Enable
    
    UBRR0H = 0;
    UBRR0L = 103; // 9600 Baud rate
    
    while(1);
}

ISR [USART0_RXC] void usart0_rx_vect(void)
{
    unsigned char data;
    data = UDR0;
    delay_ms(1);
    putch(data);
}    
