/*
 * MorseConverter.h
 *
 *  Created on: Oct 1, 2016
 *      Author: popeye
 */

#ifndef MORSECONVERTER_H_
#define MORSECONVERTER_H_
#include <string>
#include <map>

#include "IDevice.h"
#include "MorseDuration.h"

class MorseConverter {
public:
	MorseConverter(IDevice& device, MorseDuration duration);
	virtual ~MorseConverter();

	std::string Convert(std::string input);

	std::string ConvertLetter(char letter);
	std::string ConvertWord(std::string word);
	std::string ConvertPhrase(std::string phrase);
	int Play(std::string phrase);

private:
	std::string IfElseLetterConverter(char letter);
	std::string SwitchLetterConverter(char letter);
	std::string MapLetterConverter(char letter);
	void PlayDot(void);
	void PlayDash(void);

	std::map<char, std::string>  _morseMap;
	IDevice& _device;
	MorseDuration _duration;

};

#endif /* MORSECONVERTER_H_ */
