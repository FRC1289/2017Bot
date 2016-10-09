/*
 * MorseConverterTests.cpp
 *
 *  Created on: Oct 1, 2016
 *      Author: popeye
 */

#include "gtest/gtest.h"
#include "MorseConverter.h"
#include "LEDDevice.h"
#include "MorseDuration.h"

namespace {
  class MorseConverterTests : public ::testing::Test {
  public:
	  MorseConverterTests() : _converter(_device, _duration) {}
  protected:
 	  MorseDuration _duration;
 	  LEDDevice _device;
 	  MorseConverter _converter;
  };

  TEST_F(MorseConverterTests, ConvertLetterReturnsCorrectMorsePatternFor_Aa)
  {
	  ASSERT_STREQ("dD", _converter.ConvertLetter('A').c_str());
	  ASSERT_STREQ("dD", _converter.ConvertLetter('a').c_str());
  }

  TEST_F(MorseConverterTests, ConvertLetterReturnsCorrectMorsePatternFor_Bb)
  {
	  ASSERT_STREQ("Dddd", _converter.ConvertLetter('B').c_str());
	  ASSERT_STREQ("Dddd", _converter.ConvertLetter('b').c_str());
  }

  TEST_F(MorseConverterTests, ConvertLetterReturnsEmptyStringFor_NonAlphaNumeric)
  {
	  ASSERT_STREQ("", _converter.ConvertLetter('%').c_str());
  }

  TEST_F(MorseConverterTests, ConvertWordReturnsCorrectMorseWord)
  {
	  ASSERT_STREQ("dddd-d-dDdd-dDdd-DDD", _converter.ConvertWord("Hello").c_str());
  }

  TEST_F(MorseConverterTests, ConvertWordReturnsEmptyStringForWordWithNonAlphaNumeric)
  {
	  ASSERT_STREQ("", _converter.ConvertWord("Fo%&").c_str());
  }

  TEST_F(MorseConverterTests, ConvertWordReturnsAnotherCorrectMorseWord)
    {
  	  ASSERT_STREQ("dDD-DDD-dDd-dDdd-Ddd", _converter.ConvertWord("World").c_str());
    }

  TEST_F(MorseConverterTests, ConvertPhraseReturnsCorrectMorsePhrase)
  {
	  ASSERT_STREQ("dddd-d-dDdd-dDdd-DDD_dDD-DDD-dDd-dDdd-Ddd", _converter.ConvertPhrase("Hello World").c_str());
  }

  TEST_F(MorseConverterTests, PlayReturnsZeroForCorretlyFormatedMorseString)
  {
	  ASSERT_EQ(0, _converter.Play("Hello World"));
  }

  TEST_F(MorseConverterTests, PlayReturnsNonZeroForIncorrectlyFormatedMorseString)
  {
	  ASSERT_NE(0, _converter.Play("YouMustBe Kidd#$ing"));
  }
}


