/*
 * MorseConverter.cpp
 *
 *  Created on: Oct 1, 2016
 *      Author: popeye
 */
#include "MorseConverter.h"
#include <sstream>
#include <string>

MorseConverter::MorseConverter(IDevice& device, MorseDuration duration) :
	_device(device),
	_duration(duration)
{
	_morseMap['A'] = "dD"; 		_morseMap['a'] = "dD";
	_morseMap['B'] = "Dddd"; 	_morseMap['b'] = "Dddd";
	_morseMap['C'] = "DdDd"; 	_morseMap['c'] = "DdDd";
	_morseMap['D'] = "Ddd"; 	_morseMap['d'] = "Ddd";
	_morseMap['E'] = "d"; 		_morseMap['e'] = "d";
	_morseMap['F'] = "ddDd"; 	_morseMap['f'] = "ddDd";
	_morseMap['G'] = "DDd"; 	_morseMap['g'] = "DDd";
	_morseMap['H'] = "dddd"; 	_morseMap['h'] = "dddd";
	_morseMap['I'] = "dd"; 		_morseMap['i'] = "dd";
	_morseMap['J'] = "dDDD"; 	_morseMap['j'] = "dDDD";
	_morseMap['K'] = "DdD"; 	_morseMap['k'] = "DdD";
	_morseMap['L'] = "dDdd"; 	_morseMap['l'] = "dDdd";
	_morseMap['M'] = "DD"; 		_morseMap['m'] = "DD";
	_morseMap['N'] = "Dd"; 		_morseMap['n'] = "Dd";
	_morseMap['O'] = "DDD"; 	_morseMap['o'] = "DDD";
	_morseMap['P'] = "dDDd"; 	_morseMap['p'] = "dDDd";
	_morseMap['Q'] = "DDdD"; 	_morseMap['q'] = "DDdD";
	_morseMap['R'] = "dDd"; 	_morseMap['r'] = "dDd";
	_morseMap['S'] = "ddd"; 	_morseMap['s'] = "ddd";
	_morseMap['T'] = "D"; 		_morseMap['t'] = "D";
	_morseMap['U'] = "ddD"; 	_morseMap['u'] = "ddD";
	_morseMap['V'] = "dddD"; 	_morseMap['v'] = "dddD";
	_morseMap['W'] = "dDD"; 	_morseMap['w'] = "dDD";
	_morseMap['X'] = "DddD"; 	_morseMap['x'] = "DddD";
	_morseMap['Y'] = "DdDD"; 	_morseMap['y'] = "DdDD";
	_morseMap['Z'] = "Ddd"; 	_morseMap['z'] = "DDdd";
	_morseMap['1'] = "dDDDD";
	_morseMap['2'] = "ddDDD";
	_morseMap['3'] = "dddDD";
	_morseMap['4'] = "ddddD";
	_morseMap['5'] = "ddddd";
	_morseMap['6'] = "Ddddd";
	_morseMap['7'] = "DDddd";
	_morseMap['8'] = "DDDdd";
	_morseMap['9'] = "DDDDd";
	_morseMap['0'] = "DDDDD";
}

MorseConverter::~MorseConverter() {
	// TODO Auto-generated destructor stub
}

int MorseConverter::Play(std::string phrase)
{
	std::string morseString = ConvertPhrase(phrase);
	if (morseString.empty())
		return 1;

	for (char& c : morseString)
	{
		//std::cout << c << std::endl;
		switch (c)
		{
		case 'd':
			PlayDot();
			_duration.Sleep(_duration.IntraletterDuration());
			break;
		case 'D':
			PlayDash();
			_duration.Sleep(_duration.IntraletterDuration());
			break;
		case '-':
			_duration.Sleep(_duration.InterletterDuration());
			break;
		case '_':
			_duration.Sleep(_duration.InterwordDuration());
			break;
		default:
			return 1;
		}
	}
	return 0;
}

void MorseConverter::PlayDot(void)
{
	_device.Start();
	_duration.Sleep(_duration.DotDuration());
	_device.Stop();
}

void MorseConverter::PlayDash(void)
{
	_device.Start();
	_duration.Sleep(_duration.DashDuration());
	_device.Stop();
}

std::string MorseConverter::ConvertLetter(char letter)
{
	//return IfElseLetterConverter(letter);
//	return SwitchLetterConverter(letter);
	return MapLetterConverter(letter);
}

std::string MorseConverter::ConvertWord(std::string word)
{
	std::string morseWord = "";

	for (std::string::iterator it = word.begin(); it != word.end(); ++it)
	{
		std::string converted = ConvertLetter(*it);
		if (converted.empty())
			return "";

		morseWord += converted;
		morseWord += '-';
	}

	// will have an extra '-' that needs to be removed
	morseWord.pop_back();

	return morseWord;
}

std::string MorseConverter::ConvertPhrase(std::string phrase)
{
	std::stringstream reader(phrase);
	std::string word;
	std::string morsePhrase = "";

	while (reader >> word) {
		std::string converted = ConvertWord(word);
		if (converted.empty())
			return "";

		morsePhrase += converted;
		morsePhrase += '_';
	}

	// will have extra '_' to remove
	morsePhrase.pop_back();

	return morsePhrase;
}

std::string MorseConverter::MapLetterConverter(char letter)
{
	if (_morseMap.find(letter) != _morseMap.end())
	{
		return _morseMap[letter];
	}
	else
		return "";
}

std::string MorseConverter::IfElseLetterConverter(char letter)
{
	if (letter == 'A' || letter == 'a')
		return "dD";
	else if (letter == 'B' || letter == 'b')
		return "Dddd";
	else if (letter == 'C' || letter == 'c')
		return "DdDd";
	else if (letter == 'D' || letter == 'd')
		return "Ddd";
	else if (letter == 'E' || letter == 'e')
		return "d";
	else if (letter == 'F' || letter == 'f')
		return "ddDd";
	else if (letter == 'G' || letter == 'g')
		return "DDd";
	else if (letter == 'H' || letter == 'h')
		return "dddd";
	else if (letter == 'I' || letter == 'i')
		return "dd";
	else if (letter == 'J' || letter == 'j')
		return "dDDD";
	else if (letter == 'K' || letter == 'k')
		return "DdD";
	else if (letter == 'L' || letter == 'l')
		return "dDdd";
	else if (letter == 'M' || letter == 'm')
		return "DD";
	else if (letter == 'N' || letter == 'n')
		return "Dd";
	else if (letter == 'O' || letter == 'o')
		return "DDD";
	else if (letter == 'P' || letter == 'p')
		return "dDDd";
	else if (letter == 'Q' || letter == 'q')
		return "DDdD";
	else if (letter == 'R' || letter == 'r')
		return "dDd";
	else if (letter == 'S' || letter == 's')
		return "ddd";
	else if (letter == 'T' || letter == 't')
		return "D";
	else if (letter == 'U' || letter == 'u')
		return "ddD";
	else if (letter == 'V' || letter == 'v')
		return "dddD";
	else if (letter == 'W' || letter == 'w')
		return "dDD";
	else if (letter == 'X' || letter == 'x')
		return "DddD";
	else if (letter == 'Y' || letter == 'y')
			return "DdDD";
	else if (letter == 'Z' || letter == 'z')
			return "DDdd";
	else if (letter == '0')
		return "DDDDD";
	else if (letter == '1')
		return "dDDDD";
	else if (letter == '2')
		return "ddDDD";
	else if (letter == '3')
		return "dddDD";
	else if (letter == '4')
		return "ddddD";
	else if (letter == '5')
		return "ddddd";
	else if (letter == '6')
		return "Ddddd";
	else if (letter == '7')
		return "DDddd";
	else if (letter == '8')
		return "DDDdd";
	else if (letter == '9')
		return "DDDDd";
	else
		return "";
}

std::string MorseConverter::SwitchLetterConverter(char letter)
{
	switch (letter) {
	case 'A':
	case 'a':
		return "dD";
	case 'B':
	case 'b':
		return "Dddd";
	case 'C':
	case 'c':
		return "DdDd";
	case 'D':
	case 'd':
		return "Ddd";
	case 'E':
	case 'e':
		return "d";
	case 'F':
	case 'f':
		return "ddDd";
	case 'G':
	case 'g':
		return "DDd";
	case 'H':
	case 'h':
		return "dddd";
	case 'I':
	case 'i':
		return "dd";
	case 'J':
	case 'j':
		return "dDDD";
	case 'K':
	case 'k':
		return "DdD";
	case 'L':
	case 'l':
		return "dDdd";
	case 'M':
	case 'm':
		return "DD";
	case 'N':
	case 'n':
		return "Dd";
	case 'O':
	case 'o':
		return "DDD";
	case 'P':
	case 'p':
		return "dDDd";
	case 'Q':
	case 'q':
		return "DDdD";
	case 'R':
	case 'r':
		return "dDd";
	case 'S':
	case 's':
		return "ddd";
	case 'T':
	case 't':
		return "D";
	case 'U':
	case 'u':
		return "ddD";
	case 'V':
	case 'v':
		return "dddD";
	case 'W':
	case 'w':
		return "dDD";
	case 'X':
	case 'x':
		return "DddD";
	case 'Y':
	case 'y':
		return "DdDD";
	case 'Z':
	case 'z':
		return "DDdd";
	case '0':
		return "DDDDD";
	case '1':
		return "dDDDD";
	case '2':
		return "ddDDD";
	case '3':
		return "dddDD";
	case '4':
		return "ddddD";
	case '5':
		return "ddddd";
	case '6':
		return "Ddddd";
	case '7':
		return "DDddd";
	case '8':
		return "DDDdd";
	case '9':
		return "DDDDd";
	default:
		return "";
	}
}

