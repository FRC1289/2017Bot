/*
 * LEDDevice.h
 *
 *  Created on: Oct 8, 2016
 *      Author: 1289
 */

#ifndef SRC_LEDDEVICE_H_
#define SRC_LEDDEVICE_H_

#include "IDevice.h"

class LEDDevice : public IDevice {
public:
	LEDDevice() {}
	~LEDDevice() {}

	int Start(void);
	int Stop(void);
};



#endif /* SRC_LEDDEVICE_H_ */
