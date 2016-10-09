/*
 * main.cpp
 *
 *  Created on: Oct 8, 2016
 *      Author: 1289
 */

#include "LEDDevice.h"
#include  <string>

int main(int argc, char** argv)
{
	LEDDevice _device;
	MorseDuration _duration;
	MorseConverter _converter(_device, _duration);

	_converter.Play("Hello World");

}

