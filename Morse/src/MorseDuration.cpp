/*
 * MorseDuration.cpp
 *
 *  Created on: Oct 1, 2016
 *      Author: popeye
 */

#include <iostream>

#include "MorseDuration.h"

#if defined(__MINGW64__) || defined(__MINGW32__)
#include <windows.h>
#elif defined(__GNUC__)
#include <unistd.h>
#else
ASSERT(0);
#endif

MorseDuration::MorseDuration() :
	_duration(250) // 250ms
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

int MorseDuration::Sleep(int msec)
{
#if defined(__MINGW64__) || defined(__MINGW32__)
	return 0;
	// See 	http://stackoverflow.com/questions/33605828/mingw32-all-compiled-executables-hang
	// Appears that antivirus causes the Sleep call to hang, and I can't turn AV off on the Gearheadz laptops.
	//return Sleep(msec);
#elif defined(__GNUC__)
	return usleep(msec * 1000);
#else
	ASSERT(0);
#endif
}
