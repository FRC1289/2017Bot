/*
 * MorseDurationTests.cpp
 *
 *  Created on: Oct 1, 2016
 *      Author: popeye
 */

#include "gtest/gtest.h"
#include "MorseDuration.h"
namespace {
  class MorseDurationTests : public ::testing::Test {
  protected:
    MorseDuration _duration;

    int _baseDuration = 250;
  };

  TEST_F(MorseDurationTests, DotDurationIsBaseDuration)
  {
	  ASSERT_EQ(_baseDuration, _duration.DotDuration());
  }

  TEST_F(MorseDurationTests, DashDurationIs3TimesBaseDuration)
  {
	  ASSERT_EQ(3 * _baseDuration, _duration.DashDuration());
  }

  TEST_F(MorseDurationTests, IntraletterDurationIsBaseDuration)
  {
	  ASSERT_EQ(_baseDuration, _duration.IntraletterDuration());
  }

  TEST_F(MorseDurationTests, InterLetterDurationIs3TimesBaseDuration)
  {
	  ASSERT_EQ(3 * _baseDuration, _duration.InterletterDuration());
  }

  TEST_F(MorseDurationTests, InterwordDurationIs7TimesBaseDuration)
  {
	  ASSERT_EQ(7 * _baseDuration, _duration.InterwordDuration());
  }
}



