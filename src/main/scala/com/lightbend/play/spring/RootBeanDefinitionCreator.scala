/*
 * Copyright 2017 Lightbend
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lightbend.play.spring

import org.springframework.beans.MutablePropertyValues
import org.springframework.beans.factory.config.{ ConstructorArgumentValues, BeanDefinition }
import org.springframework.beans.factory.support.{ MethodOverrides, AbstractBeanDefinition, RootBeanDefinition }

object RootBeanDefinitionCreator {

  def create(bd: BeanDefinition): RootBeanDefinition = {
    val rbd = new RootBeanDefinition()
    rbd.setParentName(bd.getParentName)
    rbd.setBeanClassName(bd.getBeanClassName)
    rbd.setFactoryBeanName(bd.getFactoryBeanName)
    rbd.setFactoryMethodName(bd.getFactoryMethodName)
    rbd.setScope(bd.getScope)
    rbd.setAbstract(bd.isAbstract)
    rbd.setLazyInit(bd.isLazyInit)
    rbd.setRole(bd.getRole)
    rbd.setConstructorArgumentValues(new ConstructorArgumentValues(bd.getConstructorArgumentValues))
    rbd.setPropertyValues(new MutablePropertyValues(bd.getPropertyValues))
    rbd.setSource(bd.getSource)

    val attributeNames = bd.attributeNames
    for (attributeName <- attributeNames) {
      rbd.setAttribute(attributeName, bd.getAttribute(attributeName))
    }

    bd match {
      case originalAbd: AbstractBeanDefinition =>
        if (originalAbd.hasBeanClass) {
          rbd.setBeanClass(originalAbd.getBeanClass)
        }
        rbd.setAutowireMode(originalAbd.getAutowireMode)
        rbd.setDependencyCheck(originalAbd.getDependencyCheck)
        rbd.setDependsOn(originalAbd.getDependsOn: _*)
        rbd.setAutowireCandidate(originalAbd.isAutowireCandidate)
        rbd.copyQualifiersFrom(originalAbd)
        rbd.setPrimary(originalAbd.isPrimary)
        rbd.setNonPublicAccessAllowed(originalAbd.isNonPublicAccessAllowed)
        rbd.setLenientConstructorResolution(originalAbd.isLenientConstructorResolution)
        rbd.setInitMethodName(originalAbd.getInitMethodName)
        rbd.setEnforceInitMethod(originalAbd.isEnforceInitMethod)
        rbd.setDestroyMethodName(originalAbd.getDestroyMethodName)
        rbd.setEnforceDestroyMethod(originalAbd.isEnforceDestroyMethod)
        rbd.setMethodOverrides(new MethodOverrides(originalAbd.getMethodOverrides))
        rbd.setSynthetic(originalAbd.isSynthetic)
        rbd.setResource(originalAbd.getResource)

      case _ => rbd.setResourceDescription(bd.getResourceDescription)
    }

    rbd
  }
}
