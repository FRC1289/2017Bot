/*
 * MorseDuration.h
 *
 *  Created on: Oct 1, 2016
 *      Author: popeye
 */

#ifndef MORSEDURATION_H_
#define MORSEDURATION_H_

class MorseDuration {
public:
	MorseDuration();
	virtual ~MorseDuration();

	int DotDuration(void);
	int DashDuration(void);
	int IntraletterDuration(void);
	int InterletterDuration(void);
	int InterwordDuration(void);

private:
	int _duration;

};

#endif /* MORSEDURATION_H_ */
