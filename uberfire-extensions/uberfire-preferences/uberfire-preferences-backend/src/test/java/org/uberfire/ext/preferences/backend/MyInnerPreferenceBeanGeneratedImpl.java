/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.uberfire.ext.preferences.backend;

import javax.annotation.Generated;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.uberfire.ext.preferences.shared.bean.BasePreferenceBean;
import org.uberfire.ext.preferences.shared.bean.BasePreferencePortable;
import org.uberfire.ext.preferences.shared.bean.PreferenceBeanStore;
import org.uberfire.mvp.Command;
import org.uberfire.mvp.ParameterizedCommand;

@Dependent
@Generated("org.uberfire.ext.preferences.processors.WorkbenchPreferenceProcessor")
/*
* WARNING! This class is generated. Do not modify.
*/
public class MyInnerPreferenceBeanGeneratedImpl extends MyInnerPreference implements BasePreferenceBean<MyInnerPreference> {

    private PreferenceBeanStore store;

    @Inject
    public MyInnerPreferenceBeanGeneratedImpl( final PreferenceBeanStore store ) {
        this.store = store;
    }

    @Override
    public void load() {
        load( null );
    }

    @Override
    public void load( final ParameterizedCommand<Throwable> errorCallback ) {
        load( null, errorCallback );
    }

    @Override
    public void load( final ParameterizedCommand<MyInnerPreference> successCallback,
                      final ParameterizedCommand<Throwable> errorCallback ) {
        final MyInnerPreferenceBeanGeneratedImpl preferenceBean = this;

        store.load( new MyInnerPreferencePortableGeneratedImpl(), new ParameterizedCommand<BasePreferencePortable<MyInnerPreference>>() {
            @Override
            public void execute( final BasePreferencePortable<MyInnerPreference> portablePreference ) {
                copy( (MyInnerPreferencePortableGeneratedImpl) portablePreference, preferenceBean );
                if ( successCallback != null ) {
                    successCallback.execute( preferenceBean );
                }
            }
        }, errorCallback );
    }

    private void copy( final MyInnerPreference from,
                       final MyInnerPreference to ) {
        to.text = from.text;
    }

    @Override
    public void save() {
        save( null );
    }

    @Override
    public void save( final ParameterizedCommand<Throwable> errorCallback ) {
        save( null, errorCallback );
    }

    @Override
    public void save( final Command successCallback,
                      final ParameterizedCommand<Throwable> errorCallback ) {
        store.save( createPortableCopy(), successCallback, errorCallback );
    }

    @Override
    public void saveDefaultValue() {
        saveDefaultValue( null );
    }

    @Override
    public void saveDefaultValue( final ParameterizedCommand<Throwable> errorCallback ) {
        saveDefaultValue( null, errorCallback);
    }

    @Override
    public void saveDefaultValue( final Command successCallback,
                                  final ParameterizedCommand<Throwable> errorCallback ) {
        final MyInnerPreference defaultValue = defaultValue( new MyInnerPreferencePortableGeneratedImpl() );

        if ( defaultValue != null ) {
            if ( defaultValue instanceof MyInnerPreferencePortableGeneratedImpl ) {
                store.saveDefaultValue( (MyInnerPreferencePortableGeneratedImpl) defaultValue, successCallback, errorCallback );
            } else {
                throw new RuntimeException( "Your MyInnerPreference.defaultValue( MyInnerPreference emptyPreference ) implementation must return the emptyPreference parameter, only with its attributes modified." );
            }
        }
    }

    private BasePreferencePortable<MyInnerPreference> createPortableCopy() {
        MyInnerPreferencePortableGeneratedImpl portablePreference = new MyInnerPreferencePortableGeneratedImpl();

        copy( this, portablePreference );

        return portablePreference;
    }
}
