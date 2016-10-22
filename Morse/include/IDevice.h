/*
 * IDevice.h
 *
 *  Created on: Oct 8, 2016
 *      Author: 1289
 */

#ifndef SRC_IDEVICE_H_
#define SRC_IDEVICE_H_

class IDevice {
public:
	IDevice() {}
	~IDevice() {}

	virtual int Start(void) = 0;
	virtual int Stop(void) = 0;
};



#endif /* SRC_IDEVICE_H_ */
