/*
 * MorseDuration.cpp
 *
 *  Created on: Oct 1, 2016
 *      Author: popeye
 */

#include "MorseDuration.h"

MorseDuration::MorseDuration() :
	_duration(250)
{
	// TODO Auto-generated constructor stub

}

MorseDuration::~MorseDuration() {
	// TODO Auto-generated destructor stub
}


int MorseDuration::DotDuration(void)
{
	return _duration;
}

int MorseDuration::DashDuration(void)
{
	return 3 * _duration;
}

int MorseDuration::IntraletterDuration(void)
{
	return _duration;
}

int MorseDuration::InterletterDuration(void)
{
	return 3 * _duration;
}

int MorseDuration::InterwordDuration(void)
{
	return 7 * _duration;
}
