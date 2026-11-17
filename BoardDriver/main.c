#include <stdio.h>
#include "pico/stdlib.h"
#include "hardware/gpio.h"

int main() {
	stdio_init_all();

	// wait for usb connection
	while (!stdio_usb_connected()) {
		tight_loop_contents();
	}

	// init led pin and turn it on to indicate boot
	gpio_init(25);
	gpio_set_dir(0, GPIO_OUT);
	gpio_put(25, true);

	while(true) {

		char c = getchar();
		// non blocking version:
		// getchar_timeout_us( timeout );
		//
		// callback:
		// stdio_set_chars_available_callback();

		if (c >= 0) {
			printf("Recieved %c\n", c);
			c = 0;
		}
	}

	return 0;
}