/*
 * MorseConverter.h
 *
 *  Created on: Oct 1, 2016
 *      Author: popeye
 */

#ifndef MORSECONVERTER_H_
#define MORSECONVERTER_H_

class MorseConverter {
public:
	MorseConverter();
	virtual ~MorseConverter();

	std::string Convert(std::string input);

	std::string ConvertLetter(char letter);
	std::string ConvertWord(std::string word);
	std::string ConvertPhrase(std::string phrase);

private:
	std::string IfElseLetterConverter(char letter);
	std::string SwitchLetterConverter(char letter);
	std::string MapLetterConverter(char letter);

	std::map<char, std::string>  _morseMap;

};

#endif /* MORSECONVERTER_H_ */
